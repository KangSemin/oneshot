package salute.oneshot.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.auth.entity.OAuthProvider;
import salute.oneshot.domain.auth.entity.SocialUser;
import salute.oneshot.domain.user.entity.User;

import java.util.Optional;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {

    @Query("SELECT s.user FROM SocialUser s " +
            "WHERE s.providerId = :providerId " +
            "AND s.provider = :provider")
    Optional<User> findUserByProvider(
            @Param("providerId") String providerId,
            @Param("provider") OAuthProvider provider);
}
