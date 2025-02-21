package salute.oneshot.domain.user.entity;

import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.util.Arrays;

public enum UserRole {
    USER, ADMIN, SUPER_ADMIN;

    public static UserRole of(String userRole) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(userRole))
                .findFirst()
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_USER_ROLE));
    }
}
