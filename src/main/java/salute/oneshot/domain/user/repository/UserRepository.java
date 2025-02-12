package salute.oneshot.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndDeletedFalse(String email);

    Optional<User> findByIdAndDeletedFalse(Long id);
}
