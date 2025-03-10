package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAddressesSDto {

    private final Long userId;
    private final Long lastAddressId;
    private final int size;

    public static GetAddressesSDto of(
            Long userId,
            Long lastAddressId,
            int size
    ) {
        return new GetAddressesSDto(userId, lastAddressId, size);
    }
}
