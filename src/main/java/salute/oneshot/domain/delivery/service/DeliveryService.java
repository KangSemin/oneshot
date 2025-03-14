package salute.oneshot.domain.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.order.entity.OrderStatus;
import salute.oneshot.domain.order.repository.OrderRepository;
import salute.oneshot.domain.delivery.dto.response.AdminDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.response.UserDeliveryResponseDto;
import salute.oneshot.domain.delivery.dto.service.AdminDeliverySDto;
import salute.oneshot.domain.delivery.dto.service.DeliverySDto;
import salute.oneshot.domain.delivery.dto.service.UserDeliverySDto;
import salute.oneshot.domain.delivery.entity.Delivery;
import salute.oneshot.domain.delivery.repository.DeliveryRepository;
import salute.oneshot.global.exception.*;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public AdminDeliveryResponseDto createDelivery(DeliverySDto serviceDto) {
        if (deliveryRepository.existsByOrderId(serviceDto.getOrderId())) {
            throw new ConflictException(ErrorCode.DUPLICATE_SHIPPING);
        }

        Order order = orderRepository.findById(serviceDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ORDER_NOT_FOUND));

        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new InvalidException(ErrorCode.INVALID_ORDER_STATE);
        }

        order.updateOrderStatus(OrderStatus.IN_TRANSIT);

        Delivery delivery = Delivery.of(
                order,
                serviceDto.getReceiverName(),
                serviceDto.getReceiverPhone(),
                serviceDto.getDeliveryMessage(),
                serviceDto.getTrackingNumber(),
                serviceDto.getCourierCompany());
        deliveryRepository.save(delivery);

        return AdminDeliveryResponseDto.from(delivery);
    }

    @Transactional(readOnly = true)
    public AdminDeliveryResponseDto getDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        return AdminDeliveryResponseDto.from(delivery);
    }

    @Transactional(readOnly = true)
    public UserDeliveryResponseDto getDeliveryByOrderId(UserDeliverySDto serviceDto) {
        Delivery delivery = deliveryRepository.findByOrderId(serviceDto.getOrderId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        if (!delivery.getOrder().getUser().getId().equals(serviceDto.getUserId())) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS);
        }

        return UserDeliveryResponseDto.from(delivery);
    }

    @Transactional
    public AdminDeliveryResponseDto updateDelivery(AdminDeliverySDto serviceDto) {
        Delivery delivery = deliveryRepository.findDeliveryById(serviceDto.getDeliveryId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.SHIPPING_NOT_FOUND));

        delivery.updateStatus(serviceDto.getStatus());

        return AdminDeliveryResponseDto.from(delivery);
    }
}
