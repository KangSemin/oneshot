package salute.oneshot.domain.auth.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.auth.controller.AuthController;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.jwt.JwtProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        DefaultOAuth2User oAuth2User =
                (DefaultOAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        Long userId = (Long) attributes.get("userId");

        List<GrantedAuthority> authorities =
                new ArrayList<>(oAuth2User.getAuthorities());
        String role = authorities.get(0).getAuthority();

        String token = jwtProvider
                .createAccessToken(userId, UserRole.of(role));

        ResponseCookie cookie =  AuthController.createAccessTokenCookie(token);
        response.addHeader("Set-Cookie",cookie.toString());

        response.sendRedirect("/main");
    }
}
