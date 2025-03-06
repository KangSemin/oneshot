package salute.oneshot.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.entity.User;

@Entity
@Getter
@Table(name = "social_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;

    @Column(nullable = false)
    private String providerId;

    @Column(nullable = false)
    private String nickname;

    private SocialUser(
            User user,
            OAuthProvider provider,
            String providerId,
            String nickname
    ) {
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
        this.nickname = nickname;
    }

    public static SocialUser of(
            User user,
            OAuthProvider provider,
            String providerId,
            String nickname) {
        return new SocialUser(
                user,
                provider,
                providerId,
                nickname);
    }
}
