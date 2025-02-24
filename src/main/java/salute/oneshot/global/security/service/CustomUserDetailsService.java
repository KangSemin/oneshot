package salute.oneshot.global.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    public Authentication createAuthentication(Long userId, UserRole userRole) {
        CustomUserDetails userDetails = CustomUserDetails.of(userId, userRole);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
