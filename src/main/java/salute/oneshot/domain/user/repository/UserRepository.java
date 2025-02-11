package salute.oneshot.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salute.oneshot.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndIsDeletedIsFalse(String email);

    Optional<User> findByIdAndIsDeletedIsFalse(Long id);
}
