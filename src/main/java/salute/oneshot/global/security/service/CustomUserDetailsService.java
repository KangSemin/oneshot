package salute.oneshot.global.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserCachesRepository;
import salute.oneshot.global.security.entity.CustomUserDetails;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserCachesRepository userRoleCacheRepository;

    public UserRole loadUserById(Long id) {
        UserRole roleCache =
                userRoleCacheRepository.getCacheUserRole(id);

        if (roleCache != null) {
            return roleCache;
        }

        return userRoleCacheRepository.cacheUserRole(id);
    }

    public Authentication createAuthentication(Long userId, UserRole role) {
        CustomUserDetails userDetails = CustomUserDetails.of(userId, role);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
