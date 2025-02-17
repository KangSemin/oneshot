package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderSDto {

    private Long userId;
    private Long addressId;

    public static CreateOrderSDto of(Long userId, Long addressId) {
        return new CreateOrderSDto(userId, addressId);
    }

}