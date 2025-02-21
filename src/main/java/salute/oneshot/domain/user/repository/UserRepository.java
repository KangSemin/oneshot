package salute.oneshot.domain.user.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import salute.oneshot.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT EXISTS(SELECT 1 FROM User u WHERE u.email = :email)")
    boolean existsByEmail(@Param("email") String email);

    Optional<User> findByEmailAndIsDeletedIsFalse(String email);

    Optional<User> findByIdAndIsDeletedIsFalse(Long id);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true " +
            "WHERE u.id = :id AND u.isDeleted = false")
    int softDelete(@Param("id") Long id);
}
