package salute.oneshot.domain.shipping.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetShippingSDto {

    private Long userId;
    private Long orderId;

    public static GetShippingSDto of(Long userId, Long orderId) {
        return new GetShippingSDto(userId, orderId);
    }
}
