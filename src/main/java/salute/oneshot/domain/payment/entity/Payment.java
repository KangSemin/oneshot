package salute.oneshot.domain.payment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.jgit.dircache.Checkout;
import salute.oneshot.domain.order.entity.Order;
import salute.oneshot.domain.payment.dto.response.ConfirmPaymentResponseDto;

import javax.smartcardio.Card;

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

    public static Payment fromDto(ConfirmPaymentResponseDto dto) {
        return new Payment(dto.getOrderId(), dto.getPaymentKey());
    }

}
