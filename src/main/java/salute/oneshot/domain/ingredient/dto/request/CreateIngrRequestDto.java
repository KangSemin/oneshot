package salute.oneshot.domain.ingredient.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

@Getter
@AllArgsConstructor
public class CreateIngrRequestDto {

    @NotBlank(message = "재료명은 필수입니다.")
    private String name;
    @NotBlank(message = "설명은 필수입니다.")
    private String description;
    @NotBlank(message = "카테고리는 필수입니다.")
    private String category;

    @NotNull
    @Range(min = 0, max = 100)
    private Double avb;
}