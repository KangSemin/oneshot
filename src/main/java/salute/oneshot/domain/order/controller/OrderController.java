package salute.oneshot.domain.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.common.facade.OrderPaymentFacade;
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.request.UpdateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.CreateOrderResponseDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.dto.service.*;
import salute.oneshot.global.security.model.CustomUserDetails;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderPaymentFacade orderPaymentFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrderResponseDto>> createOrder(
            @RequestBody CreateOrderRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        CreateOrderSDto sDto = CreateOrderSDto.of(userId, requestDto.getAddressId());

        CreateOrderResponseDto responseDto = orderPaymentFacade.createOrder(sDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiResponseConst.ADD_ORD_SUCCESS, responseDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> getOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        GetOrderSDto sDto = GetOrderSDto.of(userId, orderId);

        GetOrderResponseDto responseDto = orderPaymentFacade.getOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_ORD_SUCCESS, responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CreateOrderResponseDto>>> getAllOrder(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Long userId = userDetails.getId();

        Pageable pageable = PageRequest.of(page - 1, size);

        GetAllOrderSDto sDto = GetAllOrderSDto.of(userId, pageable);

        Page<CreateOrderResponseDto> responseDtoPage = orderPaymentFacade.getAllOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_ORD_SUCCESS, responseDtoPage));
    }


    @PatchMapping("/{orderId}")
    public ResponseEntity<ApiResponse<UpdateOrderResponseDto>> updateOrder(
            @PathVariable("orderId") Long orderId,
            @Valid @RequestBody UpdateOrderRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        UpdateOrderSDto sDto = UpdateOrderSDto
                .of(userId, orderId, requestDto.getOrderStatus());

        UpdateOrderResponseDto responseDto = orderPaymentFacade.updateOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.UPDATE_ORD_SUCCESS, responseDto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        DeleteOrderSDto sDto = DeleteOrderSDto.of(userId, orderId);

        orderPaymentFacade.deleteOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.DELETE_ORD_SUCCESS));
    }
}