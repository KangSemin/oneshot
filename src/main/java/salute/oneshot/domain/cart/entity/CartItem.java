package salute.oneshot.domain.cart.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.product.entity.Product;

@Entity
@Table(name = "cart_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer amount;

    private CartItem(Cart cart, Product product, Integer amount) {
        this.cart = cart;
        this.product = product;
        this.amount = amount;
    }

    public static CartItem of(Cart foundCart, Product foundProduct, Integer amount) {
        return new CartItem(foundCart, foundProduct, amount);
    }
}
