package salute.oneshot.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.user.entity.User;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String name;
    private String description;
    private int price;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Product(String name, String description, int price, int stockQuantity, ProductCategory category, ProductStatus status, User user) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.status = (status != null) ? status : ProductStatus.SALE;
        this.user = user;
    }

    public static Product of(String name, String description, int price, int stockQuantity, ProductCategory category, ProductStatus status, User user) {
        return new Product(name, description, price, stockQuantity, category, status, user);
    }

    public void updateProduct(String name, String description, int price, int stockQuantity, ProductCategory category, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.status = status;
    }

    public void deleteProduct() {
        this.status = ProductStatus.DELETED;
    }
}
