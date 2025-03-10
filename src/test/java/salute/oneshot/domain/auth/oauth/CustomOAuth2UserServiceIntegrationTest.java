package salute.oneshot.domain.auth.oauth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import salute.oneshot.domain.auth.service.CustomOAuth2UserService;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomOAuth2UserServiceIntegrationTest {
    @Mock
    private CustomOAuth2UserService customOAuth2UserService;

    @Test
    @DisplayName("Google OAuth2 사용자 로드 테스트")
    void loadGoogleUser() {
        // given
        OAuth2UserRequest userRequest =
                createMockGoogleOAuth2UserRequest();
        OAuth2User mockOAuth2User =
                createMockGoogleOAuth2User();

        // when
        given(customOAuth2UserService.loadUser(userRequest))
                .willReturn(mockOAuth2User);
        OAuth2User loadedUser =
                customOAuth2UserService.loadUser(userRequest);

        // then
        assertNotNull(loadedUser);
        assertEquals("test@gmail.com", loadedUser.getAttribute("email"));
    }

    @Test
    @DisplayName("Naver OAuth2 사용자 로드 테스트")
    void loadNaverUser() {
        // given
        OAuth2UserRequest userRequest =
                createMockNaverOAuth2UserRequest();
        OAuth2User mockOAuth2User =
                createMockNaverOAuth2User();

        // when
        given(customOAuth2UserService.loadUser(userRequest))
                .willReturn(mockOAuth2User);
        OAuth2User loadedUser =
                customOAuth2UserService.loadUser(userRequest);

        // then
        assertNotNull(loadedUser);
        assertEquals("test@naver.com", loadedUser.getAttribute("email"));
    }


    private OAuth2UserRequest createMockGoogleOAuth2UserRequest() {
        ClientRegistration clientRegistration =
                ClientRegistration.withRegistrationId("google")
                        .clientId("test-client-id")
                        .clientSecret("test-client-secret")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .redirectUri("http://localhost:8080/login/oauth2/code/google")
                        .scope("profile", "email")
                        .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                        .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                        .userNameAttributeName("sub")
                        .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth") // 이 부분 추가
                        .build();
        OAuth2AccessToken auth2AccessToken =
                new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                        "mock-access-token",
                        Instant.now(),
                        Instant.now().plusSeconds(3600));
        return new OAuth2UserRequest(clientRegistration, auth2AccessToken);
    }

    private OAuth2UserRequest createMockNaverOAuth2UserRequest() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("naver")
                .clientId("test-naver-client-id")
                .clientSecret("test-naver-client-secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/login/oauth2/code/naver")
                .scope("nickname", "email")
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                .tokenUri("https://nid.naver.com/oauth2.0/token")
                .userInfoUri("https://openapi.naver.com/v1/nid/me")
                .userNameAttributeName("response")
                .build();

        OAuth2AccessToken accessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                "mock-naver-access-token",
                Instant.now(),
                Instant.now().plusSeconds(3600)
        );

        return new OAuth2UserRequest(clientRegistration, accessToken);
    }

    private OAuth2User createMockGoogleOAuth2User() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "test@gmail.com");
        attributes.put("sub", "google-test-id");
        attributes.put("name", "Test User");

        return new DefaultOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "sub"
        );
    }

    private OAuth2User createMockNaverOAuth2User() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "test@naver.com");
        attributes.put("id", "naver-test-id");
        attributes.put("nickname", "Test User");

        return new DefaultOAuth2User(
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "id"
        );
    }
}
