package salute.oneshot.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.cart.entity.Cart;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber; //주문 번호

    private String name; // "토스 티셔츠 외 2건"
    private Long amount; // 15000 (원으로 고정)

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // TODO:Cart로 유저에 접근 가능한데 유저와의 연관관계가 필요한지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private Order (String orderNumber, String name, Long amount, User user ,Cart cart, Address address, List<OrderItem> orderItems) {
        this.status = OrderStatus.PENDING_PAYMENT;
        this.orderNumber = orderNumber;
        this.name = name;
        this.amount = amount;
        this.user = user;
        this.cart = cart;
        this.address = address;
        this.orderItems = orderItems;
    }

    public static Order of (String orderNumber, String name, Long amount, User user ,Cart cart, Address address, List<OrderItem> orderItems) {
        return new Order(orderNumber, name, amount, user, cart, address, orderItems);
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public Boolean isValidStatusChange (OrderStatus currentStatus, OrderStatus newStatus) {
        if(currentStatus == OrderStatus.PENDING_PAYMENT && newStatus == OrderStatus.PROCESSING) {
            return true;
        } else if(currentStatus == OrderStatus.PROCESSING && newStatus == OrderStatus.PENDING_SHIPMENT) {
            return true;
        } else if(currentStatus == OrderStatus.PENDING_SHIPMENT && newStatus == OrderStatus.IN_TRANSIT) {
            return true;
        } else if(currentStatus == OrderStatus.IN_TRANSIT && newStatus == OrderStatus.SHIPPED) {
            return true;
        }
        return false;
    }

    public void updateOrderStatus (OrderStatus status) {
        this.status = status;
    }
}
