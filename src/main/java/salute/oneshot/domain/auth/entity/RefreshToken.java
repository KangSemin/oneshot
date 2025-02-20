package salute.oneshot.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

@Entity
@Table(name = "refresh_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {

    @Id
    private Long userId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private long expireAt;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isUsed = false;

    public RefreshToken(
            Long userId,
            String token,
            long expiresAt
    ) {
        this.userId = userId;
        this.token = token;
        this.expireAt = expiresAt;
    }

    public static RefreshToken of(
            Long userId,
            String token,
            long expireAt
    ) {
        return new RefreshToken(
                userId,
                token,
                expireAt
        );
    }
}
