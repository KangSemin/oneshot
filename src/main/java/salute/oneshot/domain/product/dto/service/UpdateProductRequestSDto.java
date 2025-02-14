package salute.oneshot.domain.product.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateProductRequestSDto {

    private Long userId;
    private String name;
    private String description;
    private ProductCategory category;
    private int price;
    private int stockQuantity;
    private ProductStatus status;
    private Long productId;

    public static UpdateProductRequestSDto of(Long userId, String name, String description, ProductCategory category,
                                              int price, int stockQuantity, ProductStatus status, Long productId) {

        return new UpdateProductRequestSDto(userId, name, description, category, price, stockQuantity, status, productId);
    }
}