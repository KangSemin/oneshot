package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAddressSdto {

    private final String addressName;
    private final String postcode;
    private final String postAddress;
    private final String detailAddress;
    private final String extraAddress;
    private final Long userId;

    public static CreateAddressSdto of(
            String addressName,
            String postcode,
            String postAddress,
            String detailAddress,
            String extraAddress,
            Long userId
    ) {
        return new CreateAddressSdto(
                addressName,
                postcode,
                postAddress,
                detailAddress,
                extraAddress,
                userId
        );
    }
}
