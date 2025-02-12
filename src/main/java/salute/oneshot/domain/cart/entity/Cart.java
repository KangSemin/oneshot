package salute.oneshot.domain.cart.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.entity.User;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Cart 엔티티를 불러오는 경우에 itemList를 불러오지 않는 경우가 없다고 판단하고 즉시 로딩으로 설정
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CartItem> itemList;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isOrdered = false;

    public static Cart from(User user) {
        return new Cart(user);
    }

    private Cart(User user) {
        this.user = user;
    }
}
