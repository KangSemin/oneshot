package salute.oneshot.domain.product.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeleteProductSDto {

    private final Long userId;
    private final Long productId;

    public static DeleteProductSDto of(Long userId, Long productId) {
        return new DeleteProductSDto(userId, productId);
    }
}