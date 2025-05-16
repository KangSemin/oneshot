package salute.oneshot.global.security.filter;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.model.SecurityConst;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.global.security.service.CustomUserDetailsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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

        String token =
                Optional.ofNullable(request.getHeader(SecurityConst.AUTHORIZATION_HEADER))
                .map(jwtProvider::extractToken)
                .orElseGet(() -> getTokenFromCookie(request));

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
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

    private String getTokenFromCookie(HttpServletRequest request) {
        return Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(c -> c.getName().equals("accessToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        }
}
