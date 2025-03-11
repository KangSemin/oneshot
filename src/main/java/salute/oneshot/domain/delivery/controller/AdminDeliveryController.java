package salute.oneshot.domain.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.delivery.dto.request.DeliveryRequestDto;
import salute.oneshot.domain.delivery.dto.request.UpdateDeliveryRequestDto;
import salute.oneshot.domain.delivery.dto.response.AdminDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.service.AdminDeliverySDto;
import salute.oneshot.domain.delivery.dto.service.DeliverySDto;
import salute.oneshot.domain.delivery.service.DeliveryService;

@RestController
@RequestMapping("/api/admin/deliveries")
@RequiredArgsConstructor
public class AdminDeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ApiResponse<AdminDeliveryResponseDto>> createDelivery(
            @RequestBody DeliveryRequestDto requestDto
    ) {
        DeliverySDto serviceDto = DeliverySDto.of(
                requestDto.getOrderId(),
                requestDto.getReceiverName(),
                requestDto.getReceiverPhone(),
                requestDto.getDeliveryMessage(),
                requestDto.getTrackingNumber(),
                requestDto.getCourierCompany());
        AdminDeliveryResponseDto responseDto =
                deliveryService.createDelivery(serviceDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        ApiResponseConst.ADD_DELIVERY_SUCCESS,
                        responseDto));
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<AdminDeliveryResponseDto>> getDelivery(
            @PathVariable Long deliveryId
    ) {
        AdminDeliveryResponseDto responseDto =
                deliveryService.getDelivery(deliveryId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.GET_DELIVERY_SUCCESS,
                        responseDto));
    }

    @PatchMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<AdminDeliveryResponseDto>> updateDelivery(
            @PathVariable Long deliveryId,
            @RequestBody UpdateDeliveryRequestDto requestDto
    ) {
        AdminDeliverySDto serviceDto =
                AdminDeliverySDto.of(deliveryId, requestDto.getStatus());
        AdminDeliveryResponseDto responseDto =
                deliveryService.updateDelivery(serviceDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(
                        ApiResponseConst.UPDATE_DELIVERY_SUCCESS,
                        responseDto));
    }
}
