package salute.oneshot.domain.ingredient.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchIngrSDto {
    private String keyword;
    private String category;
    private Pageable pageable;

    public static SearchIngrSDto of(String keyword, String category, Pageable pageable){
        return new SearchIngrSDto(keyword, category, pageable);
    }
}
