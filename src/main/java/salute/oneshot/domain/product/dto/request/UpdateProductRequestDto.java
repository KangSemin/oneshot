package salute.oneshot.domain.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

@Getter
@AllArgsConstructor
public class UpdateProductRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private ProductCategory category;

    @NotNull
    private int price;

    @NotNull
    private int stockQuantity;

    private ProductStatus status;
}