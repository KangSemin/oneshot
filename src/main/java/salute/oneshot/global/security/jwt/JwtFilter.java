package salute.oneshot.global.security.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.SecurityConst;
import salute.oneshot.global.security.service.CustomUserDetailsService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(SecurityConst.AUTHORIZATION_HEADER);
        if (!StringUtils.hasText(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = jwtProvider.extractToken(authorizationHeader);
            setAuthentication(token);
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            log.error("", e);
            response.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    ErrorCode.EXPIRED_TOKEN.getMessage());
        } catch (JwtException e) {
            log.error("", e);
            response.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    ErrorCode.INVALID_TOKEN.getMessage());
        }
    }

    private boolean isAuthEndpoint(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/auth/") ||
                request.getRequestURI().startsWith("/api/payments") ||
                request.getRequestURI().startsWith("/addresses") ||
                request.getRequestURI().startsWith("/payments");
    }

    private String extractToken(String bearerToken) {
        if (!StringUtils.hasText(bearerToken) ||
                !bearerToken.startsWith(SecurityConst.BEARER_PREFIX)) {
            throw new MalformedJwtException(SecurityConst.MALFORMED_TOKEN);
        }
        return bearerToken.substring(SecurityConst.BEARER_PREFIX.length());
    }

    private Authentication createAuthentication(Claims claims) {
        Long id = Long.parseLong(claims.getSubject());
        UserRole role = UserRole.of(claims.get("role", String.class));

        return userDetailsService.createAuthentication(id, role);
    }

    private void setAuthentication(String token) {
        Claims claims = jwtProvider.parseClaims(token);
        jwtProvider.validateToken(token);
        Authentication authentication = createAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
