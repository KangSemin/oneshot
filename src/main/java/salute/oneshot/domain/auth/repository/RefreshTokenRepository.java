package salute.oneshot.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.auth.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);

    @Modifying
    @Query(value = "INSERT INTO refresh_token (user_id, token, expire_at, created_at, modified_at) " +
            "VALUES (:userId, :token, :expireAt, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) " +
            "ON DUPLICATE KEY UPDATE " +
            "token = :token, expire_at = :expireAt, modified_at = CURRENT_TIMESTAMP",
            nativeQuery = true)
    void upsertRefreshToken(
            @Param("userId") Long userId,
            @Param("token") String token,
            @Param("expireAt") long expireAt
    );
}
