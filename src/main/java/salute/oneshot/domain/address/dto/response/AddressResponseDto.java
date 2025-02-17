package salute.oneshot.domain.address.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.address.entity.Address;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressResponseDto {

    private final Long addressId;
    private final String addressName;
    private final String postcode;
    private final String postAddress;
    private final String detailAddress;
    private final String extraAddress;
    private final boolean isDefault;

    public static AddressResponseDto from(Address address) {
        return new AddressResponseDto(
                address.getId(),
                address.getAddressName(),
                address.getPostcode(),
                address.getPostAddress(),
                address.getDetailAddress(),
                address.getExtraAddress(),
                address.isDefault());
    }
}
