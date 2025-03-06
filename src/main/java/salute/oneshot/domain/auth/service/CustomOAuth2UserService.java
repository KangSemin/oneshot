    package salute.oneshot.domain.auth.service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
    import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
    import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
    import org.springframework.security.oauth2.core.user.OAuth2User;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import salute.oneshot.domain.auth.entity.OAuthProvider;
    import salute.oneshot.domain.auth.entity.SocialUser;
    import salute.oneshot.domain.auth.model.GoogleUser;
    import salute.oneshot.domain.auth.model.NaverUser;
    import salute.oneshot.domain.auth.model.ProviderUser;
    import salute.oneshot.domain.auth.repository.SocialUserRepository;
    import salute.oneshot.domain.user.entity.UserRole;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Service
    @RequiredArgsConstructor
    public class CustomOAuth2UserService extends DefaultOAuth2UserService {

        private final SocialUserRepository socialUserRepository;

        @Override
        @Transactional
        public OAuth2User loadUser(OAuth2UserRequest userRequest) {
            OAuth2User oAuth2User = super.loadUser(userRequest);
            OAuthProvider oAuthProvider = OAuthProvider.of(
                    userRequest.getClientRegistration().getRegistrationId());

            ProviderUser providerUser =
                    getProviderUser(oAuth2User, oAuthProvider);
            SocialUser socialUser =
                    findOrCreateUser(providerUser);

            Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
            attributes.put("providerId", providerUser.getProviderId());
            attributes.put("provider", providerUser.getProvider());
            attributes.put("socialUserId", socialUser.getId());

            List<GrantedAuthority> authorities = new ArrayList<>(oAuth2User.getAuthorities());
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.name()));

            return new DefaultOAuth2User(
                    authorities,
                    attributes,
                    userRequest.getClientRegistration().getProviderDetails()
                            .getUserInfoEndpoint().getUserNameAttributeName());
        }

        private SocialUser findOrCreateUser(ProviderUser providerUser) {
            return socialUserRepository.findByProviderIdAndProvider(
                            providerUser.getProviderId(),
                            providerUser.getProvider())
                    .orElseGet(() -> createUser(providerUser));
        }

        private SocialUser createUser(ProviderUser providerUser) {
            SocialUser socialUser = SocialUser.of(
                    null,
                    providerUser.getProvider(),
                    providerUser.getProviderId());

            return socialUserRepository.save(socialUser);
        }

        private static ProviderUser getProviderUser(
                OAuth2User oAuth2User,
                OAuthProvider provider
        ) {
            return switch (provider) {
                case NAVER ->
                        NaverUser.of(oAuth2User, provider);
                case GOOGLE ->
                        GoogleUser.of(oAuth2User, provider);
            };
        }
    }
