package salute.oneshot.domain.auth.service;


import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import salute.oneshot.domain.auth.handler.OAuth2SuccessHandler;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.jwt.JwtProvider;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OAuth2SuccessHandlerTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Authentication authentication;

    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @BeforeEach
    void setUp() {
        oAuth2SuccessHandler = new OAuth2SuccessHandler(jwtProvider);
    }

    @Test
    @DisplayName("OAuth2 인증 성공 핸들러 테스트")
    void onAuthenticationSuccess() throws Exception {
        // given
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("userId", 1L);

        DefaultOAuth2User oAuth2User = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "userId"
        );

        given(authentication.getPrincipal()).willReturn(oAuth2User);
        given(jwtProvider.createAccessToken(1L, UserRole.USER))
                .willReturn("mock-access-token");

        // when
        oAuth2SuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // then
        verify(jwtProvider).createAccessToken(1L, UserRole.USER);
        verify(response).addHeader(anyString(), anyString());
        verify(response).sendRedirect("/main");
    }
}
