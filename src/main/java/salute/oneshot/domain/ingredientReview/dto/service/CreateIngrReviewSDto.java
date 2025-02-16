package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateIngrReviewSDto {

    private Long userId;
    private Long ingredientId;
    private Byte star;
    private String content;

    public static CreateIngrReviewSDto of(Long userId, Long ingredientId, Byte star, String content) {
        return new CreateIngrReviewSDto(userId, ingredientId, star, content);
    }

}
