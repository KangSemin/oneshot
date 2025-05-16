package salute.oneshot.domain.address.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressPageResponseDto {

    private final List<AddressBriefResponseDto> addresses;
    private final boolean hasNext;
    private final Long nextCursor;

    public static AddressPageResponseDto of(
            List<AddressBriefResponseDto> addresses,
            boolean hasNext,
            Long nextCursor
    ) {
        return new AddressPageResponseDto(
                addresses,
                hasNext,
                nextCursor);
    }
}
