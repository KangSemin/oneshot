package salute.oneshot.domain.product.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductSDto {

    private final Long userId;
    private final String name;
    private final String description;
    private final ProductCategory category;
    private final int price;
    private final int stockQuantity;
    private final ProductStatus status;

    public static CreateProductSDto of(Long userId, String name, String description, ProductCategory category,
                                       int price, int stockQuantity, ProductStatus status) {

        return new CreateProductSDto(userId, name, description, category, price, stockQuantity, status);
    }

}
