package salute.oneshot.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.common.dto.error.ErrorResponse;
import salute.oneshot.global.security.SecurityConst;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.warn(SecurityConst.AUTH_ERROR_LOG,
                authException.getClass().getSimpleName(),
                authException.getMessage()
        );

        ErrorCode errorCode = ErrorCode.UNAUTHORIZED_ACCESS;

        if (authException instanceof BadCredentialsException) {
            errorCode = ErrorCode.LOGIN_FAILED;
        }

        ErrorResponse errorResponse = ErrorResponse.of(errorCode);

        response.setContentType(SecurityConst.CONTENT_TYPE);
        response.setStatus(errorCode.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
