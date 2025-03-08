package salute.oneshot.domain.address.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import salute.oneshot.config.TestSecurityConfig;
import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressDetailResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.service.AddressService;
import salute.oneshot.domain.common.AbstractRestDocsTests;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.filter.JwtFilter;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.util.AddressTestFactory;
import salute.oneshot.util.UserTestFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AddressController.class)
@Import(TestSecurityConfig.class)
class AddressControllerTest extends AbstractRestDocsTests {

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @MockitoBean
    private AddressService addressService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @MockitoBean
    private JwtFilter jwtFilter;

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
                AddressTestFactory.createDetailResponseDto(address);

        given(addressService.createAddress(any(CreateAddressSdto.class)))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/api/addresses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                        .with(user(UserTestFactory.createMockUserDetails())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(ApiResponseConst.ADD_ADR_SUCCESS))
                .andExpect(jsonPath("$.data.addressName").value(AddressTestFactory.ADDRESS_NAME))
                .andExpect(jsonPath("$.data.postAddress").value(AddressTestFactory.POST_ADDRESS))
                .andReturn();
    }
}