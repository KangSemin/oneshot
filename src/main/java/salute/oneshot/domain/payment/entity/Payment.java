package salute.oneshot.domain.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.payment.dto.feign.TossPaymentResponseDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

//    // TODO: fetch type 고민
//    @ManyToOne
//    @JoinColumn(name = "order_id")
    private String orderId;

    private String paymentKey;

    private Payment(String orderId, String paymentKey) {
        this.orderId = orderId;
        this.paymentKey = paymentKey;
    }

    public static Payment fromDto(TossPaymentResponseDto dto) {
        return new Payment(dto.getOrderId(), dto.getPaymentKey());
    }

}
