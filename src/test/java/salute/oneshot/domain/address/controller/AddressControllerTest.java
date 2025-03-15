package salute.oneshot.domain.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.request.UpdateAddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressBriefResponseDto;
import salute.oneshot.domain.address.dto.response.AddressDetailResponseDto;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.service.AddressSDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
import salute.oneshot.domain.address.dto.service.UpdateAddressSDto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.service.AddressService;
import salute.oneshot.domain.common.*;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.util.AddressTestFactory;
import salute.oneshot.util.UserTestFactory;
import salute.oneshot.global.exception.NotFoundException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
@Import(TestSecurityConfig.class)
class AddressControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = AddressTestFactory.createAddress();
    }

    @DisplayName("주소등록 성공")
    @Test
    void successCreateAddress() throws Exception {
        // given
        CreateAddressRequestDto requestDto =
                AddressTestFactory.createCreateRequestDto();
        AddressDetailResponseDto responseDto =
                AddressDetailResponseDto.from(address);

        given(addressService.createAddress(any(CreateAddressSdto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_ADR_SUCCESS))
                .andExpect(jsonPath("$.data.addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addressName").value(AddressTestFactory.ADDRESS_NAME))
                .andExpect(jsonPath("$.data.postcode").value(AddressTestFactory.POSTCODE))
                .andExpect(jsonPath("$.data.postAddress").value(AddressTestFactory.POST_ADDRESS))
                .andExpect(jsonPath("$.data.detailAddress").value(AddressTestFactory.DETAIL_ADDRESS))
                .andExpect(jsonPath("$.data.extraAddress").value(AddressTestFactory.EXTRA_ADDRESS))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-create-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_POST_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_POST_API")))
                .andReturn();
    }

    @DisplayName("주소 목록 조회 성공")
    @Test
    void successGetAddresses() throws Exception {
        // given
        Address address1 = AddressTestFactory.createAddress();
        Address address2 = AddressTestFactory.createAddress();

        List<AddressBriefResponseDto> addressPage = Arrays.asList(
                AddressBriefResponseDto.from(address1),
                AddressBriefResponseDto.from(address2));

        AddressPageResponseDto responseDto =
                AddressPageResponseDto.of(addressPage, true, 2L);

        given(addressService.getAddresses(any(GetAddressesSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/addresses")
                        .param("lastAddressId", String.valueOf(AddressTestFactory.ADDRESS_ID))
                        .param("size", String.valueOf(AddressTestFactory.DEFAULT_PAGE_SIZE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ADR_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.addresses[0].addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addresses[1].addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addresses").isArray())
                .andExpect(jsonPath("$.data.addresses.length()").value(2))
                .andExpect(jsonPath("$.data.hasNext").value(true))
                .andExpect(jsonPath("$.data.nextCursor").value(2))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-get-addresses",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_LIST_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_LIST_API"),
                        ApiDocumentFactory.LAST_ID_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("첫 페이지 주소 목록 조회 성공")
    @Test
    void successGetAddressesFirstPage() throws Exception {
        // given
        Address address = AddressTestFactory.createAddress();

        List<AddressBriefResponseDto> addressPage = List.of(
                AddressBriefResponseDto.from(address));

        AddressPageResponseDto responseDto =
                AddressPageResponseDto.of(addressPage, false, 1L);

        given(addressService.getAddresses(any(GetAddressesSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/addresses")
                        .param("size", String.valueOf(AddressTestFactory.DEFAULT_PAGE_SIZE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ADR_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.addresses[0].addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addresses").isArray())
                .andExpect(jsonPath("$.data.addresses.length()").value(1))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-get-addresses-first-page",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_LIST_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_LIST_API"),
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("빈 주소 목록 조회 성공")
    @Test
    void successGetAddressesEmptyList() throws Exception {
        // given
        List<AddressBriefResponseDto> addressPage = new ArrayList<>();

        AddressPageResponseDto responseDto =
                AddressPageResponseDto.of(addressPage, false, null);

        given(addressService.getAddresses(any(GetAddressesSDto.class))).willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/addresses")
                        .param("lastAddressId", "10")
                        .param("size", String.valueOf(AddressTestFactory.DEFAULT_PAGE_SIZE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ADR_LIST_SUCCESS))
                .andExpect(jsonPath("$.data.addresses").isArray())
                .andExpect(jsonPath("$.data.addresses.length()").value(0))
                .andExpect(jsonPath("$.data.hasNext").value(false))
                .andExpect(jsonPath("$.data.nextCursor").isEmpty())
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-get-addresses-empty-list",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_LIST_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_LIST_API"),
                        ApiDocumentFactory.LAST_ID_PARAM,
                        ApiDocumentFactory.SIZE_PARAM))
                .andReturn();
    }

    @DisplayName("주소 조회 성공")
    @Test
    void successGetAddress() throws Exception {
        // given
        AddressDetailResponseDto responseDto =
                AddressDetailResponseDto.from(address);
        given(addressService.getAddress(any(AddressSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(get("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.GET_ADR_SUCCESS))
                .andExpect(jsonPath("$.data.addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addressName").value(AddressTestFactory.ADDRESS_NAME))
                .andExpect(jsonPath("$.data.postcode").value(AddressTestFactory.POSTCODE))
                .andExpect(jsonPath("$.data.postAddress").value(AddressTestFactory.POST_ADDRESS))
                .andExpect(jsonPath("$.data.detailAddress").value(AddressTestFactory.DETAIL_ADDRESS))
                .andExpect(jsonPath("$.data.extraAddress").value(AddressTestFactory.EXTRA_ADDRESS))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-get-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_GET_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_GET_API")))
                .andReturn();
    }

    @DisplayName("주소 조회 실패: 존재하지 않는 주소로 조회 시도")
    @Test
    void InvalidAddressIdGetAddress() throws Exception {
        // given
        given(addressService.getAddress(any(AddressSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        // when & give
        mockMvc.perform(get("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.ADR_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/invalid-address-id-get-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_GET_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_GET_API")))
                .andReturn();
    }

    @DisplayName("주소 수정 성공")
    @Test
    void successUpdateAddress() throws Exception {
        // given
        UpdateAddressRequestDto requestDto =
                AddressTestFactory.createUpdateRequestDto();
        address.updateAddress(
                requestDto.getAddressName(),
                requestDto.getPostcode(),
                requestDto.getPostAddress(),
                requestDto.getDetailAddress(),
                requestDto.getExtraAddress(),
                requestDto.isDefault());

        AddressDetailResponseDto responseDto =
                AddressDetailResponseDto.from(address);

        given(addressService.updateAddress(any(UpdateAddressSDto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(patch("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.UPDATE_ADR_SUCCESS))
                .andExpect(jsonPath("$.data.addressId").value(AddressTestFactory.ADDRESS_ID))
                .andExpect(jsonPath("$.data.addressName").value(AddressTestFactory.NEW_ADDRESS_NAME))
                .andExpect(jsonPath("$.data.postcode").value(AddressTestFactory.NEW_POSTCODE))
                .andExpect(jsonPath("$.data.postAddress").value(AddressTestFactory.NEW_POST_ADDRESS))
                .andExpect(jsonPath("$.data.detailAddress").value(AddressTestFactory.NEW_DETAIL_ADDRESS))
                .andExpect(jsonPath("$.data.extraAddress").value(AddressTestFactory.EXTRA_ADDRESS))
                .andExpect(jsonPath("$.data.default").value(AddressTestFactory.IS_DEFAULT))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-update-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("주소 수정 실패: 디폴트주소인 주소의 기본값 여부를 False로 변경 시도")
    @Test
    void invalidDefaultUpdateAddress() throws Exception {
        // given
        UpdateAddressRequestDto requestDto =
                AddressTestFactory.createUpdateRequestDto();
        address.setDefault();

        given(addressService.updateAddress(any(UpdateAddressSDto.class)))
                .willThrow(new InvalidException(ErrorCode.DEFAULT_ADDRESS_REQUIRED));

        // when & then
        mockMvc.perform(patch("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DEFAULT_ADDRESS_REQUIRED.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/invalid-default-update-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("주소 수정 실패: 존재하지 않는 주소로 수정 시도")
    @Test
    void invalidAddressIdUpdateAddress() throws Exception {
        // given
        UpdateAddressRequestDto requestDto =
                AddressTestFactory.createUpdateRequestDto();

        given(addressService.updateAddress(any(UpdateAddressSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        // when & then
        mockMvc.perform(patch("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.ADR_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/invalid-address-id-update-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_UPDATE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_UPDATE_API")))
                .andReturn();
    }

    @DisplayName("주소 삭제 성공")
    @Test
    void successDeleteAddress() throws Exception {
        // given
        given(addressService.deleteAddress(any(AddressSDto.class)))
                .willReturn(AddressTestFactory.ADDRESS_ID);

        // when & then
        mockMvc.perform(delete("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.DELETE_ADR_SUCCESS))
                .andExpect(jsonPath("$.data").value(AddressTestFactory.ADDRESS_ID))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/success-delete-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_DELETE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_DELETE_API")))
                .andReturn();
    }

    @DisplayName("주소 삭제 실패: 디폴트주소 삭제 시도")
    @Test
    void invalidDefaultDeleteAddress() throws Exception {
        // given

        // 테스트 코드엔 영향을 주지 않음, 테스트 의도를 명확히 하기 위해 표기
        address.setDefault();

        given(addressService.deleteAddress(any(AddressSDto.class)))
                .willThrow(new InvalidException(ErrorCode.DEFAULT_ADDRESS));

        // when & then
        mockMvc.perform(delete("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.DEFAULT_ADDRESS.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/invalid-default-delete-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_DELETE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_DELETE_API")))
                .andReturn();
    }

    @DisplayName("주소 삭제 실패: 존재하지 않는 주소로 조회 시도")
    @Test
    void invalidAddressIdDeleteAddress() throws Exception {
        // given
        given(addressService.deleteAddress(any(AddressSDto.class)))
                .willThrow(new NotFoundException(ErrorCode.ADR_NOT_FOUND));

        // when & then
        mockMvc.perform(delete("/api/addresses/{addressId}", AddressTestFactory.ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(ErrorCode.ADR_NOT_FOUND.getMessage()))
                .andDo(ApiDocumentFactory.listDoc(
                        "address-controller-test/invalid-address-id-delete-address",
                        ApiDocumentFactory.ADDRESS_TAG,
                        ApiDocumentationLoader.getSummary("address", "ADDRESS_DELETE_API"),
                        ApiDocumentationLoader.getDescription("address", "ADDRESS_DELETE_API")))
                .andReturn();
    }
}