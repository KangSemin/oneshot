package salute.oneshot.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.error.ErrorResponse;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.SecurityConst;
import salute.oneshot.global.security.service.CustomUserDetailsService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearerJwt = request.getHeader(SecurityConst.AUTHORIZATION_HEADER);

        if (bearerJwt == null) {
            sendErrorResponse(response, ErrorCode.TOKEN_NOT_FOUND);
            return;
        }

        try {
            authenticateUser(bearerJwt);
            filterChain.doFilter(request, response);

        } catch (SecurityException e) {
            log.error(SecurityConst.TOKEN_VALIDATION_FAIL, e);
            sendErrorResponse(response, ErrorCode.TOKEN_VALIDATION_FAIL);
        } catch (MalformedJwtException e) {
            log.error(SecurityConst.MALFORMED_TOKEN, e);
            sendErrorResponse(response, ErrorCode.MALFORMED_TOKEN);
        } catch (ExpiredJwtException e) {
            log.error(SecurityConst.EXPIRED_TOKEN, e);
            sendErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.error(SecurityConst.UNSUPPORTED_TOKEN, e);
            sendErrorResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        } catch (Exception e) {
            log.error(SecurityConst.UNKNOWN_TOKEN_ERROR, e);
            sendErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void authenticateUser(String bearerJwt) {
        String jwt = extractToken(bearerJwt);
        Claims claims = jwtProvider.extractClaims(jwt);

        Long id = Long.parseLong(claims.getSubject());
        String email = claims.get("email", String.class);
        UserRole role = UserRole.of(claims.get("role", String.class));

        Authentication authentication =
                userDetailsService.createAuthentication(id, email, role);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String extractToken(String bearerToken) {
        if (!StringUtils.hasText(bearerToken) ||
                !bearerToken.startsWith(SecurityConst.BEARER_PREFIX)) {
            throw new MalformedJwtException(SecurityConst.MALFORMED_TOKEN);
        }
        return bearerToken.substring(SecurityConst.BEARER_PREFIX.length());
    }

    private void sendErrorResponse(
            HttpServletResponse response,
            ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        response.setContentType(SecurityConst.CONTENT_TYPE);
        response.setStatus(errorCode.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
