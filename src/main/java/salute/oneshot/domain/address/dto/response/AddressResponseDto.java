package salute.oneshot.domain.address.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.address.entity.Address;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressResponseDto {

    private Long addressId;
    private String addressName;
    private String postcode;
    private String postAddress;
    private String detailAddress;
    private String extraAddress;

    public static AddressResponseDto from(Address address) {
        return new AddressResponseDto(
                address.getId(),
                address.getAddressName(),
                address.getPostcode(),
                address.getPostAddress(),
                address.getDetailAddress(),
                address.getExtraAddress());
    }
}
