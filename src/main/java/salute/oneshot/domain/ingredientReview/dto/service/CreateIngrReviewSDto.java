package salute.oneshot.domain.ingredientReview.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateIngrReviewSDto {

    private final Long userId;
    private final Long ingredientId;
    private final Byte star;
    private final String content;

    public static CreateIngrReviewSDto of(Long userId, Long ingredientId, Byte star, String content) {
        return new CreateIngrReviewSDto(userId, ingredientId, star, content);
    }

}
