package salute.oneshot.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProductRequestDto {

    @NotBlank(message = "상품명은 필수입니다.")
    private final String name;

    @NotBlank(message = "상품 설명은 필수입니다.")
    private final String description;

    @NotNull(message = "상품 카테고리는 필수입니다.")
    private final ProductCategory category;

    @NotNull(message = "상품 가격은 필수입니다.")
    private final int price;

    @NotNull(message = "상품 재고는 필수입니다.")
    private final int stockQuantity;

    private final ProductStatus status;

}
