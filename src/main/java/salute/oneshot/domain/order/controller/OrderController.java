package salute.oneshot.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.common.dto.success.ApiResponse;
import salute.oneshot.domain.common.dto.success.ApiResponseConst;
import salute.oneshot.domain.order.dto.request.CreateOrderRequestDto;
import salute.oneshot.domain.order.dto.response.OrderResponseDto;
import salute.oneshot.domain.order.dto.service.CreateOrderSDto;
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
}