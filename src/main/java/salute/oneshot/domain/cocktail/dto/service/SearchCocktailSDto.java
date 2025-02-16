package salute.oneshot.domain.cocktail.dto.service;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchCocktailSDto {

    private final List<Long> ingrientIds;
    private final int page;
    private final int size;

    public static SearchCocktailSDto of(List<Long> ingrientIds, int page, int size) {
        return new SearchCocktailSDto(ingrientIds, page, size);
    }

}
