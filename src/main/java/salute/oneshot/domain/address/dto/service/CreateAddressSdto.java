package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAddressSdto {

    private String addressName;
    private String postcode;
    private String postAddress;
    private String detailAddress;
    private String extraAddress;
    private Long userId;

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
