package salute.oneshot.domain.product.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.Product;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponseDto {

    private Long productId;
    private String name;
    private String description;
    private int price;
    private int stockQuantity;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(
                product.getId(), product.getName(), product.getDescription(),
                product.getPrice(), product.getStockQuantity());
    }
}
