package salute.oneshot.global.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final UserRepository userRepository;

    public void validateToken(Long userId, Date issuedAt) {
        LocalDateTime issuedDateTime = issuedAt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        if (!userRepository.isValidToken(userId, issuedDateTime)) {
            throw new ExpiredJwtException(null, null, "");
        }
    }
}
