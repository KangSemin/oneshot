package salute.oneshot.domain.order.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllOrderSDto {

    private final Long userId;
    private final Pageable pageable;

    public static GetAllOrderSDto of(Long userId, Pageable pageable) {
        return new GetAllOrderSDto(userId, pageable);
    }

}