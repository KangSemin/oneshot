package salute.oneshot.domain.shipping.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.shipping.dto.request.ShippingRequestDto;
import salute.oneshot.domain.shipping.dto.response.AdminShippingResponseDto;
import salute.oneshot.domain.shipping.dto.service.ShippingSDto;
import salute.oneshot.domain.shipping.service.ShippingService;

@RestController
@RequestMapping("/api/admin/shipping")
@RequiredArgsConstructor
public class AdminShippingController {

    private final ShippingService shippingService;

    @PostMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<AdminShippingResponseDto>> createShipping(
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
        AdminShippingResponseDto responseDto =
                shippingService.createShipping(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_SHIPPING_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{shippingId}")
    public ResponseEntity<ApiResponse<AdminShippingResponseDto>> getShipping(
            @PathVariable Long shippingId
    ) {
        AdminShippingResponseDto responseDto =
                shippingService.getShipping(shippingId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_SHIPPING_SUCCESS,
                        responseDto));
    }
}
