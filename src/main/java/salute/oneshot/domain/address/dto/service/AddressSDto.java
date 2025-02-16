package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressSDto {

    private Long userId;
    private Long addressId;

    public static AddressSDto of(Long userId, Long addressId) {
        return new AddressSDto(userId, addressId);
    }
}
