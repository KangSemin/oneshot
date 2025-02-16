package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateAddressSDto {

    private String addressName;
    private String postcode;
    private String postAddress;
    private String detailAddress;
    private String extraAddress;
    private boolean isDefault;
    private Long userId;
    private Long addressId;

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
