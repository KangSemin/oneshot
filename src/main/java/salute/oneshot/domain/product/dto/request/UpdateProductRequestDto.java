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
public class UpdateProductRequestDto {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    private final ProductCategory category;

    @NotNull
    private final int price;

    @NotNull
    private final int stockQuantity;

    private final ProductStatus status;
}