package salute.oneshot.domain.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.shipping.dto.response.UserShippingResponseDto;
import salute.oneshot.domain.shipping.dto.service.UserShippingSDto;
import salute.oneshot.domain.shipping.service.ShippingService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<UserShippingResponseDto>> getShipping(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        UserShippingSDto serviceDto =
                UserShippingSDto.of(userDetails.getId(), orderId);
        UserShippingResponseDto responseDto =
                shippingService.getShippingByOrderId(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_SHIPPING_SUCCESS,
                        responseDto));
    }
}
