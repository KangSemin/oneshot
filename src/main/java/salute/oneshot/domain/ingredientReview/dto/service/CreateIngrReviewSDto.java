package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.Getter;

@Getter
public class CreateIngrReviewSDto {

    private Long userId;
    private Long ingredientId;
    private Byte star;
    private String content;

    private CreateIngrReviewSDto(Long userId, Long ingredientId, Byte star, String content) {
        this.userId = userId;
        this.ingredientId = ingredientId;
        this.star = star;
        this.content = content;
    }

    public static CreateIngrReviewSDto of(Long userId, Long ingredientId, Byte star, String content) {
        return new CreateIngrReviewSDto(userId, ingredientId, star, content);
    }

}
