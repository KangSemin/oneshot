package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final SocialUserRepository socialUserRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuthProvider oAuthProvider = OAuthProvider.of(
                userRequest.getClientRegistration().getRegistrationId());

        ProviderUser providerUser =
                getProviderUser(oAuth2User, oAuthProvider);

        User user = findOrCreateUser(providerUser);

        Map<String, Object> attributes =
                new HashMap<>(oAuth2User.getAttributes());
        attributes.put("providerId", providerUser.getProviderId());
        attributes.put("provider", providerUser.getProvider());
        attributes.put("userId", user.getId());

        List<GrantedAuthority> authorities =
                new ArrayList<>(oAuth2User.getAuthorities());
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));

        return new DefaultOAuth2User(
                authorities,
                attributes,
                userRequest.getClientRegistration()
                           .getProviderDetails()
                           .getUserInfoEndpoint()
                           .getUserNameAttributeName());
    }

    private User findOrCreateUser(ProviderUser providerUser) {
        return socialUserRepository.findUserByProvider(
                        providerUser.getProviderId(),
                        providerUser.getProvider())
                .orElseGet(() -> createUser(providerUser));
    }

    private User createUser(ProviderUser providerUser) {
        User user = userRepository.findByEmail(providerUser.getEmail())
                .orElseGet(() -> createSocialUser(providerUser));
        SocialUser socialUser = SocialUser.of(
                user,
                providerUser.getProvider(),
                providerUser.getProviderId());
        user.addSocialUser(socialUser);

        return userRepository.save(user);
    }

    private User createSocialUser(ProviderUser providerUser) {
        String tempPassword = UUID.randomUUID().toString();
        return User.of(
                providerUser.getEmail(),
                tempPassword,
                providerUser.getNickname());
    }

    private static ProviderUser getProviderUser(
            OAuth2User oAuth2User,
            OAuthProvider provider
    ) {
        return switch (provider) {
            case NAVER -> NaverUser.of(oAuth2User, provider);
            case GOOGLE -> GoogleUser.of(oAuth2User, provider);
        };
    }
}
