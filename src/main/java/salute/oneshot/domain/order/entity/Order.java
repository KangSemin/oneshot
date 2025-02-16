package salute.oneshot.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.user.entity.User;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String name; // "토스 티셔츠 외 2건"
    private Long amount; // 15000 (원으로 고정)
    private OrderStatus status;

    // TODO:Cart로 유저에 접근 가능한데 유저와의 연관관계가 필요한지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
