package salute.oneshot.domain.address.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressResponseDto;
import salute.oneshot.domain.address.dto.service.CreateAddressSdto;
import salute.oneshot.domain.address.service.AddressService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody CreateAddressRequestDto request
    ) {
        CreateAddressSdto serviceDto =
                CreateAddressSdto.of(
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
                .body(responseDto);
    }
}
