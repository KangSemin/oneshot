package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAddressSDto {

    private final String addressName;
    private final String postcode;
    private final String postAddress;
    private final String detailAddress;
    private final String extraAddress;
    private final boolean isDefault;
    private final Long userId;
    private final Long addressId;

    public static UpdateAddressSDto of(
            String addressName,
            String postcode,
            String postAddress,
            String detailAddress,
            String extraAddress,
            boolean isDefault,
            Long userId,
            Long addressId
    ) {
        return new UpdateAddressSDto(
                addressName,
                postcode,
                postAddress,
                detailAddress,
                extraAddress,
                isDefault,
                userId,
                addressId
        );
    }
}
