package salute.oneshot.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.auth.entity.SocialUser;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_email_is_deleted", columnList = "email, is_deleted")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BigInt")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialUser> socialUsers = new ArrayList<>();

    @Column
    private LocalDateTime isDeletedAt;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private boolean isDeleted = false;

    private User(
            String email,
            String password,
            String nickname,
            UserRole userRole
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    private User(
            String email,
            String password,
            UserRole userRole
    ) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public static User of(
            String email,
            String password,
            String nickname
    ) {
        return new User(email, password, nickname, UserRole.USER);
    }

    public static User of(
            String email,
            String password,
            String nickname,
            UserRole role
    ) {
        return new User(email, password, nickname, role);
    }

    public static User of(
            String email,
            String password,
            UserRole userRole
    ) {
        return new User(email, password, userRole);
    }

    public void update(String nickname, String password) {
        this.nickname = nickname;
        if (password != null) {
            this.password = password;
        }
    }

    public void addSocialUser(SocialUser socialUser) {
        this.socialUsers.add(socialUser);
    }
}
