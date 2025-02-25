package salute.oneshot.domain.ingredient.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchIngrSDto {
    private String name;
    private String description;
    private String category;
    private Pageable pageable;

    public static SearchIngrSDto of(String name, String description, String category, Pageable pageable){
        return new SearchIngrSDto(name, description, category, pageable);
    }
}
