package salute.oneshot.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.service.UserService;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserService userService;

    public Authentication createAuthentication(Long id, String email, UserRole role) {
        CustomUserDetails userDetails = CustomUserDetails.of(id, email, role);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
