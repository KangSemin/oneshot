package salute.oneshot.domain.user.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM User u WHERE u.email = :email)")
    boolean existsByEmail(@Param("email") String email);

    Optional<User> findByEmailAndIsDeletedIsFalse(String email);

    Optional<User> findByIdAndIsDeletedIsFalse(Long id);

    @Query("SELECT u.userRole FROM User u WHERE u.id = :id")
    Optional<UserRole> findUserRoleById(@Param("id") Long id);
}
