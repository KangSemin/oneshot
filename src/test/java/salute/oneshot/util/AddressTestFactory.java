package salute.oneshot.util;

import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.response.AddressDetailResponseDto;
import salute.oneshot.domain.address.entity.Address;

public class AddressTestFactory {
    public static final Long USER_ID = 1L;
    public static final Long ADDRESS_ID = 1L;
    public static final String ADDRESS_NAME = "집";
    public static final String POSTCODE = "12345";
    public static final String POST_ADDRESS = "서울시 강서구";
    public static final String DETAIL_ADDRESS = "아파트 101동 202호";
    public static final String EXTRA_ADDRESS = "배송전 연락주세요.";
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static CreateAddressRequestDto createCreateRequestDto() {
        return CreateAddressRequestDto.of(
                ADDRESS_NAME,
                POSTCODE,
                POST_ADDRESS,
                DETAIL_ADDRESS,
                EXTRA_ADDRESS);
    }

    public static AddressDetailResponseDto createDetailResponseDto(Address address) {
        return AddressDetailResponseDto.from(address);
    }

    public static Address createAddress() {
        return Address.of(
                ADDRESS_NAME,
                POSTCODE,
                POST_ADDRESS,
                DETAIL_ADDRESS,
                EXTRA_ADDRESS,
                USER_ID);
    }
}
