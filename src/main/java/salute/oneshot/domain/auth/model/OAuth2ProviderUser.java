package salute.oneshot.domain.auth.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import salute.oneshot.domain.auth.entity.OAuthProvider;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class OAuth2ProviderUser implements ProviderUser {

    protected final OAuth2User oAuth2User;
    protected final OAuthProvider provider;
    private final Map<String, Object> attributes;
    private final Long userId;

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public OAuthProvider getProvider() {
        return provider;
    }
}
