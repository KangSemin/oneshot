package salute.oneshot.domain.order.entity;

public enum OrderStatus {
    PENDING_PAYMENT,    // 결제 대기 (오직 OrderService.updateOrderStatusAfterPaymentIsDone()에서만 수정 가능)
    PROCESSING,         // 주문 처리 중
    PENDING_SHIPMENT,   // 배송 대기 중
    IN_TRANSIT,         // 배송 중
    SHIPPED,            // 배송 완료
    CANCELLED           // 취소
}
