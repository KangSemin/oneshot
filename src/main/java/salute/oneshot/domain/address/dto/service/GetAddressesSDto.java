package salute.oneshot.domain.address.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAddressesSDto {

    private Long userId;
    private Pageable pageable;

    public static GetAddressesSDto of(Long userId, Pageable pageable) {
        return new GetAddressesSDto(userId, pageable);
    }
}
