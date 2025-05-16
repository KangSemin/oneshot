package salute.oneshot.domain.delivery.controller;

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
import salute.oneshot.domain.delivery.dto.response.UserDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.service.UserDeliverySDto;
import salute.oneshot.domain.delivery.service.DeliveryService;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ApiResponse<UserDeliveryResponseDto>> getShipping(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId
    ) {
        UserDeliverySDto serviceDto =
                UserDeliverySDto.of(userDetails.getId(), orderId);
        UserDeliveryResponseDto responseDto =
                deliveryService.getDeliveryByOrderId(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_DELIVERY_SUCCESS,
                        responseDto));
    }
}
