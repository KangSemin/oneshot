package salute.oneshot.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.auth.entity.SocialUser;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.repository.UserRepository;

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

    @Column(name = "nick_name")
    private String nickName;

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
            String nickName,
            UserRole userRole
    ) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
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
            String nickName
    ) {
        return new User(email, password, nickName, UserRole.USER);
    }

    public static User of(
            String email,
            String password,
            String nickName,
            UserRole role
    ) {
        return new User(email, password, nickName, role);
    }

    public static User of(
            String email,
            String password,
            UserRole userRole
    ) {
        return new User(email, password, userRole);
    }

    public void update(String nickName, String password) {
        this.nickName = nickName;
        if (password != null) {
            this.password = password;
        }
    }

    public void addSocialUser(SocialUser socialUser) {
        this.socialUsers.add(socialUser);
    }
}
