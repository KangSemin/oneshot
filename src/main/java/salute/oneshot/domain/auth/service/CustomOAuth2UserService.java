    package salute.oneshot.domain.auth.service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
    import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
    import org.springframework.security.oauth2.core.user.OAuth2User;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import salute.oneshot.domain.auth.entity.OAuthProvider;
    import salute.oneshot.domain.auth.entity.SocialUser;
    import salute.oneshot.domain.auth.model.GoogleUser;
    import salute.oneshot.domain.auth.model.NaverUser;
    import salute.oneshot.domain.auth.model.ProviderUser;
    import salute.oneshot.domain.user.entity.User;
    import salute.oneshot.domain.user.entity.UserRole;
    import salute.oneshot.domain.user.repository.UserRepository;

    import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class CustomOAuth2UserService extends DefaultOAuth2UserService {

        private final UserRepository userRepository;
        private static final String TEMP_DOMAIN = "@social-user.com";

        @Override
        @Transactional
        public OAuth2User loadUser(OAuth2UserRequest userRequest) {
            OAuth2User oAuth2User = super.loadUser(userRequest);
            OAuthProvider oAuthProvider = OAuthProvider.of(
                    userRequest.getClientRegistration().getRegistrationId());

            User user = findOrCreateUser(oAuth2User, oAuthProvider);

            ProviderUser providerUser =
                    getProviderUser(oAuth2User, oAuthProvider, user.getId());

            return new DefaultCustomOAuth2User(providerUser, userRequest);
        }

        private User findOrCreateUser(
                OAuth2User oAuth2User,
                OAuthProvider oAuthProvide
        ) {
            ProviderUser providerUser = getProviderUser
                    (oAuth2User, oAuthProvide, null);

            return userRepository.findBySocialUser(
                            providerUser.getProviderId(),
                            providerUser.getProvider())
                    .orElseGet(() -> createUser(providerUser));
        }

        private User createUser(ProviderUser providerUser) {
            String tempEmail = providerUser.getProviderId() + TEMP_DOMAIN ;
            String tempPassword = UUID.randomUUID().toString();
            User user = User.of(tempEmail, tempPassword, UserRole.USER);

            SocialUser socialUser = SocialUser.of(
                    user,
                    providerUser.getProvider(),
                    providerUser.getProviderId());
            user.addSocialUser(socialUser);

            return userRepository.save(user);
        }

        private static ProviderUser getProviderUser(
                OAuth2User oAuth2User,
                OAuthProvider provider,
                Long userId
        ) {
            return switch (provider) {
                case NAVER ->
                        NaverUser.of(oAuth2User, provider, userId);
                case GOOGLE ->
                        GoogleUser.of(oAuth2User, provider, userId);
            };
        }
    }
