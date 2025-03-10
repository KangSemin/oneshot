package salute.oneshot.domain.ingredient.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UpdateIngrRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    @NotBlank @JsonIgnore
    private MultipartFile imageFile;

    @NotNull
    @Range(min = 0, max = 100)
    private Double avb;

}
