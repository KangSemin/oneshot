package salute.oneshot.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salute.oneshot.domain.auth.entity.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Modifying
    @Query(value = "INSERT INTO refresh_token " +
            "(user_id, token, expire_at, is_used, created_at, modified_at) " +
            "VALUES (:userId, :token, :expireAt, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) " +
            "ON DUPLICATE KEY UPDATE " +
            "token = :token, expire_at = :expireAt, is_used = false, modified_at = CURRENT_TIMESTAMP",
            nativeQuery = true)
    void upsertRefreshToken(
            @Param("userId") Long userId,
            @Param("token") String token,
            @Param("expireAt") long expireAt
    );

    @Modifying
    @Query("UPDATE RefreshToken r SET r.isUsed = true " +
            "WHERE r.userId = :userId AND r.isUsed = false AND r.token = :refreshToken")
    int validateAndUseToken(
            @Param("userId") Long userId,
            @Param("refreshToken") String refreshToken
    );
}
