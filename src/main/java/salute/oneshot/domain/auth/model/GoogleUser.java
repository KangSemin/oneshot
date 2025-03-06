package salute.oneshot.domain.auth.model;

import org.springframework.security.oauth2.core.user.OAuth2User;
import salute.oneshot.domain.auth.entity.OAuthProvider;

public class GoogleUser extends OAuth2ProviderUser {

    private static final String GOOGLE_ID_KEY = "sub";

    private GoogleUser(
            OAuth2User oAuth2User,
            OAuthProvider provider
    ) {
        super(oAuth2User,
                provider,
                oAuth2User.getAttributes());
    }

    public static GoogleUser of(
            OAuth2User oAuth2User,
            OAuthProvider provider
    ) {
        return new GoogleUser(oAuth2User, provider);
    }

    @Override
    public String getProviderId() {
        return (String) getAttributes().get(GOOGLE_ID_KEY);
    }

    @Override
    public String getNameAttributeKey() {
        return GOOGLE_ID_KEY;
    }
}
