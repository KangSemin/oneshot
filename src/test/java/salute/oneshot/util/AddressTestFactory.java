package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.address.dto.request.CreateAddressRequestDto;
import salute.oneshot.domain.address.dto.request.UpdateAddressRequestDto;
import salute.oneshot.domain.address.entity.Address;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AddressTestFactory {
    public static final Long USER_ID = 1L;
    public static final Long ADDRESS_ID = 1L;
    public static final String ADDRESS_NAME = "집";
    public static final String NEW_ADDRESS_NAME = "새집";
    public static final String POSTCODE = "12345";
    public static final String NEW_POSTCODE = "23456";
    public static final String POST_ADDRESS = "서울시 강서구";
    public static final String NEW_POST_ADDRESS = "서울시 강남구";
    public static final String DETAIL_ADDRESS = "아파트 101동 202호";
    public static final String NEW_DETAIL_ADDRESS = "아파트 202동 303호";
    public static final String EXTRA_ADDRESS = "배송전 연락주세요.";
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final boolean IS_DEFAULT = false;

    public static Address createAddress() {
        Address address = Address.of(
                ADDRESS_NAME,
                POSTCODE,
                POST_ADDRESS,
                DETAIL_ADDRESS,
                EXTRA_ADDRESS,
                USER_ID);
        ReflectionTestUtils.setField(address, "id", ADDRESS_ID);

        return address;
    }

    public static CreateAddressRequestDto createCreateRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<CreateAddressRequestDto> addressCont = CreateAddressRequestDto.class.
                getDeclaredConstructor(
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class);
        addressCont.setAccessible(true);

        return addressCont.newInstance(
                ADDRESS_NAME,
                POSTCODE,
                POST_ADDRESS,
                DETAIL_ADDRESS,
                EXTRA_ADDRESS);
    }

    public static UpdateAddressRequestDto createUpdateRequestDto()
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<UpdateAddressRequestDto> addressCont = UpdateAddressRequestDto.class
                .getDeclaredConstructor(
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        boolean.class);
        addressCont.setAccessible(true);

        return addressCont.newInstance(
                NEW_ADDRESS_NAME,
                NEW_POSTCODE,
                NEW_POST_ADDRESS,
                NEW_DETAIL_ADDRESS,
                EXTRA_ADDRESS,
                IS_DEFAULT);
    }
}
