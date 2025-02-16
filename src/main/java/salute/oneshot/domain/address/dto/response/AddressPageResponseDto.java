package salute.oneshot.domain.address.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressPageResponseDto {

    private List<AddressResponseDto> addresses;
    private int currentPage;
    private int totalPage;
    private boolean hasNext;

    public static AddressPageResponseDto from(
            Page<AddressResponseDto> pageResponseDto
    ) {
        return new AddressPageResponseDto(
                pageResponseDto.getContent(),
                pageResponseDto.getNumber(),
                pageResponseDto.getTotalPages(),
                pageResponseDto.hasNext()
        );
    }
}
