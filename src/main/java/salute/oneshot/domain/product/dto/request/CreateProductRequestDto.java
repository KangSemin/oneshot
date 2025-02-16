package salute.oneshot.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

@Getter
@AllArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "상품명은 필수입니다.")
    private String name;

    @NotBlank(message = "상품 설명은 필수입니다.")
    private String description;

    @NotNull(message = "상품 카테고리는 필수입니다.")
    private ProductCategory category;

    @NotNull(message = "상품 가격은 필수입니다.")
    private int price;

    @NotNull(message = "상품 재고는 필수입니다.")
    private int stockQuantity;

    private ProductStatus status;

}
