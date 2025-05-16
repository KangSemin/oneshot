package salute.oneshot.domain.product.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllProductSDto {

    private final String category;
    private final Pageable pageable;

    public static GetAllProductSDto of(String category, Pageable pageable) {
        return new GetAllProductSDto(category, pageable);
    }
}