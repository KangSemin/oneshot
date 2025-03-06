package salute.oneshot.domain.auth.model;

import salute.oneshot.domain.auth.entity.OAuthProvider;

import java.util.Map;

public interface ProviderUser {
    String getProviderId();
    Map<String, Object> getAttributes();
    OAuthProvider getProvider();
    String getNameAttributeKey();
}
