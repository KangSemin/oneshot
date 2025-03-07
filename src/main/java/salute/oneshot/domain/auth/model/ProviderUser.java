package salute.oneshot.domain.auth.model;

import salute.oneshot.domain.auth.entity.OAuthProvider;

import java.util.Map;

public interface ProviderUser {
    String getProviderId();
    String getEmail();
    String getNickname();
    Map<String, Object> getAttributes();
    OAuthProvider getProvider();
    String getNameAttributeKey();
}
