package salute.oneshot.domain.auth.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import salute.oneshot.domain.auth.model.ProviderUser;
import salute.oneshot.domain.user.entity.UserRole;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class DefaultCustomOAuth2User implements OAuth2User {

    private final ProviderUser providerUser;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String userNameAttributeName;

    public DefaultCustomOAuth2User(
            ProviderUser providerUser,
            OAuth2UserRequest userRequest
    ) {
        this.providerUser = providerUser;
        this.authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + UserRole.USER.name()));
        this.userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return providerUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return providerUser.getProviderId();
    }
}
