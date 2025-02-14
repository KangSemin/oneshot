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
import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressPageResponseDto;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.AddressSdto;
import salute.oneshot.domain.address.dto.service.GetAddressSDto;
import salute.oneshot.domain.address.dto.service.GetAddressesSDto;
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
            @Valid @RequestBody CreateAddressRequestDto request
    ) {
        AddressSdto serviceDto =
                AddressSdto.of(
                        request.getAddressName(),
                        request.getPostcode(),
                        request.getPostAddress(),
                        request.getDetailAddress(),
                        request.getExtraAddress(),
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
        GetAddressSDto serviceDto =
                GetAddressSDto.of(userDetails.getId(), addressId);
        AddressResponseDto responseDto =
                addressService.getAddress(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_ADR_SUCCESS,
                        responseDto));
    }
}
