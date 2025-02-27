package salute.oneshot.domain.auth.model;

import org.springframework.security.oauth2.core.user.OAuth2User;
import salute.oneshot.domain.auth.entity.OAuthProvider;

import java.util.Map;

public class NaverUser extends OAuth2ProviderUser {

    private static final String NAVER_ID_KEY = "id";

    private NaverUser(
            OAuth2User oAuth2User,
            OAuthProvider provider,
            Long userId
    ) {
        super(oAuth2User, provider, extractAttrs(oAuth2User), userId);
    }

    public static NaverUser of(
            OAuth2User oAuth2User,
            OAuthProvider provider,
            Long userId
    ) {
        return new NaverUser(oAuth2User, provider, userId);
    }

    @Override
    public String getProviderId() {
        return (String) getAttributes().get(NAVER_ID_KEY);
    }

    @Override
    public String getNameAttributeKey() {
        return NAVER_ID_KEY;
    }

    @SuppressWarnings("unchecked")
        private static Map<String, Object> extractAttrs(
                OAuth2User oAuth2User
    ) {
        return (Map<String, Object>) oAuth2User
                .getAttributes().get("response");
    }
}
