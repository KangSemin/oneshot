package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAddressSDto {

    private Long userId;
    private Long addressId;

    public static GetAddressSDto of(Long userId, Long addressId) {
        return new GetAddressSDto(userId, addressId);
    }
}
