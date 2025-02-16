package salute.oneshot.domain.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.shipping.dto.request.ShippingRequestDto;
import salute.oneshot.domain.shipping.dto.response.CreateShippingResponseDto;
import salute.oneshot.domain.shipping.dto.service.ShippingSDto;
import salute.oneshot.domain.shipping.service.ShippingService;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<CreateShippingResponseDto>> createShipping(
            @PathVariable Long orderId,
            @RequestBody ShippingRequestDto requestDto
    ) {
        ShippingSDto serviceDto = ShippingSDto.of(
                orderId,
                requestDto.getReceiverName(),
                requestDto.getReceiverPhone(),
                requestDto.getDeliveryMessage(),
                requestDto.getTrackingNumber(),
                requestDto.getCourierCompany());
        CreateShippingResponseDto responseDto =
                shippingService.createShipping(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_SHIPPING_SUCCESS,
                        responseDto));
    }
}
