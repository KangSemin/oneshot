package salute.oneshot.domain.favorite.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetFavoritesSDto {

    private Long id;
    private Pageable pageable;

    public static GetFavoritesSDto of(Long id, Pageable pageable) {
        return new GetFavoritesSDto(id, pageable);
    }
}
