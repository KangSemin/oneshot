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
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.request.UpdateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.GetOrderResponseDto;
import salute.oneshot.domain.order.dto.response.OrderResponseDto;
import salute.oneshot.domain.order.dto.response.UpdateOrderResponseDto;
import salute.oneshot.domain.order.dto.service.CreateOrderSDto;
import salute.oneshot.domain.order.dto.service.GetAllOrderSDto;
import salute.oneshot.domain.order.dto.service.GetOrderSDto;
import salute.oneshot.domain.order.dto.service.UpdateOrderSDto;
import salute.oneshot.domain.order.service.OrderService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> createOrder(
            @RequestBody CreateOrderRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        CreateOrderSDto sDto = CreateOrderSDto.of(userId, requestDto.getAddressId());

        OrderResponseDto responseDto = orderService.createOrder(sDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(ApiResponseConst.ADD_ORD_SUCCESS, responseDto));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<GetOrderResponseDto>> getOrder(
            @PathVariable("orderId") Long orderId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        GetOrderSDto sDto = GetOrderSDto.of(userId, orderId);

        GetOrderResponseDto responseDto = orderService.getOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.GET_ORD_SUCCESS, responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderResponseDto>>> getAllOrder(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        Long userId = userDetails.getId();

        Pageable pageable = PageRequest.of(page - 1, size);

        GetAllOrderSDto sDto = GetAllOrderSDto.of(userId, pageable);

        Page<OrderResponseDto> responseDtoPage = orderService.getAllOrder(sDto);

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

        UpdateOrderResponseDto responseDto = orderService.updateOrder(sDto);

        return ResponseEntity.ok(ApiResponse.success(ApiResponseConst.UPDATE_ORD_SUCCESS, responseDto));
    }

}