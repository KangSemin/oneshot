package salute.oneshot.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.auth.entity.OAuthProvider;
import salute.oneshot.domain.auth.entity.SocialUser;

import java.util.Optional;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {
    Optional<SocialUser> findByProviderIdAndProvider(String providerId, OAuthProvider provider);
}
