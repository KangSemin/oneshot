package salute.oneshot.domain.cart.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.cart.dto.request.UpdateCartItemAmountRequestDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateItemQuantitySDto {
    private final Long userId;
    private final Long itemId;
    private final Integer quantity;

    public static UpdateItemQuantitySDto of(Long userId, Long itemId, UpdateCartItemAmountRequestDto dto) {
        return new UpdateItemQuantitySDto(userId, itemId, dto.getQuantity());
    }
}
