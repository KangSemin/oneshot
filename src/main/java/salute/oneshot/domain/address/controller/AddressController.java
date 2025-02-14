package salute.oneshot.domain.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.address.dto.request.AddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.dto.service.AddressSDto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
import salute.oneshot.domain.address.dto.service.UpdateAddressSDto;
import salute.oneshot.domain.address.service.AddressService;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<ApiResponse<AddressResponseDto>> createAddress(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddressRequestDto requestDto
    ) {
        CreateAddressSdto serviceDto = CreateAddressSdto.of(
                requestDto.getAddressName(),
                requestDto.getPostcode(),
                requestDto.getPostAddress(),
                requestDto.getDetailAddress(),
                requestDto.getExtraAddress(),
                userDetails.getId()
        );
        AddressResponseDto responseDto =
                addressService.createAddress(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_ADR_SUCCESS,
                        responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AddressPageResponseDto>> getAddresses(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(
                page - 1, size, Sort.by("modifiedAt").descending()
        );
        GetAddressesSDto serviceDto =
                GetAddressesSDto.of(userDetails.getId(), pageable);

        AddressPageResponseDto responseDto =
                addressService.getAddresses(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ADR_LIST_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<ApiResponse<AddressResponseDto>> getAddress(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long addressId
    ) {
        AddressSDto serviceDto =
                AddressSDto.of(userDetails.getId(), addressId);
        AddressResponseDto responseDto =
                addressService.getAddress(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ADR_SUCCESS,
                        responseDto));
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<ApiResponse<AddressResponseDto>> updateAddress(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody AddressRequestDto requestDto,
            @PathVariable Long addressId
    ) {
        UpdateAddressSDto serviceDto = UpdateAddressSDto.of(
                requestDto.getAddressName(),
                requestDto.getPostcode(),
                requestDto.getPostAddress(),
                requestDto.getDetailAddress(),
                requestDto.getExtraAddress(),
                userDetails.getId(),
                addressId
        );
        AddressResponseDto responseDto =
                addressService.updateAddress(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_ADR_SUCCESS,
                        responseDto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse<Long>> deleteAddress(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long addressId) {
        AddressSDto serviceDto =
                AddressSDto.of(userDetails.getId(), addressId);
        Long deletedAddress =
                addressService.deleteAddress(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.DELETE_ADR_SUCCESS,
                        deletedAddress));
    }
}
