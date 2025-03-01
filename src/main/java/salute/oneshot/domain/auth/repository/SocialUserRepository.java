package salute.oneshot.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.auth.entity.SocialUser;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long> {
}
