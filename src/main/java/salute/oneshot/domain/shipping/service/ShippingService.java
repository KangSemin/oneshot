package salute.oneshot.domain.shipping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.shipping.dto.response.AdminShippingResponseDto;
import salute.oneshot.domain.shipping.dto.response.CreateShippingResponseDto;
import salute.oneshot.domain.shipping.dto.response.UserShippingResponseDto;
import salute.oneshot.domain.shipping.dto.service.GetShippingSDto;
import salute.oneshot.domain.shipping.dto.service.ShippingSDto;
import salute.oneshot.domain.shipping.entity.Shipping;
import salute.oneshot.domain.shipping.repository.ShippingRepository;
import salute.oneshot.global.exception.*;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public CreateShippingResponseDto createShipping(ShippingSDto serviceDto) {
        if (shippingRepository.existsByOrderId(serviceDto.getOrderId())) {
            throw new ConflictException(ErrorCode.DUPLICATE_SHIPPING);
        }

        Order order = orderRepository.findById(serviceDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if (order.getStatus() != OrderStatus.PENDING_SHIPMENT) {
            throw new InvalidException(ErrorCode.INVALID_ORDER_STATE);
        }

        order.updateStatus(OrderStatus.IN_TRANSIT);

        Shipping shipping = Shipping.of(
                order,
                serviceDto.getReceiverName(),
                serviceDto.getReceiverPhone(),
                serviceDto.getDeliveryMessage(),
                serviceDto.getTrackingNumber(),
                serviceDto.getCourierCompany());
        shippingRepository.save(shipping);

        return CreateShippingResponseDto.from(shipping);
    }

    @Transactional(readOnly = true)
    public AdminShippingResponseDto getShipping(Long shippingId) {
        Shipping shipping = shippingRepository.findById(shippingId)
                        .orElseThrow(() -> new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        return AdminShippingResponseDto.from(shipping);
    }

    @Transactional(readOnly = true)
    public UserShippingResponseDto getShippingByOrderId(Long orderId, Long userId) {
        Shipping shipping = shippingRepository.findByOrderId(orderId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        if (!shipping.getOrder().getUser().getId().equals(userId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS);
        }

        return UserShippingResponseDto.from(shipping);
    }
}
