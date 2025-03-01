package salute.oneshot.domain.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.auth.controller.AuthController;
import salute.oneshot.domain.auth.model.ProviderUser;
import salute.oneshot.domain.auth.service.DefaultCustomOAuth2User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.jwt.JwtProvider;

import java.io.IOException;

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

        DefaultCustomOAuth2User defaultCustomOAuth2User =
                (DefaultCustomOAuth2User) authentication.getPrincipal();

        ProviderUser providerUser =
                defaultCustomOAuth2User.getProviderUser();

        String token = jwtProvider
                .createAccessToken(providerUser.getUserId(), UserRole.USER);

        ResponseCookie cookie =  AuthController.createAccessTokenCookie(token);
        response.addHeader("Set-Cookie",cookie.toString());

        response.sendRedirect("/main");
    }
}
