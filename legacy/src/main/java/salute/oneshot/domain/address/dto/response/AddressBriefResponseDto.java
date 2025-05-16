package salute.oneshot.domain.address.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.address.entity.Address;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressBriefResponseDto {

    private final Long addressId;
    private final String addressName;
    private final String postAddress;
    private final String detailAddress;
    private final boolean isDefault;

    public static AddressBriefResponseDto from(Address address) {
        return new AddressBriefResponseDto(
                address.getId(),
                address.getAddressName(),
                address.getPostcode(),
                address.getPostAddress(),
                address.isDefault());
    }
}
