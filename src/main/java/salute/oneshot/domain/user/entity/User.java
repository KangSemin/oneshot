package salute.oneshot.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

import java.util.Optional;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BigInt")
    private Long id;

    private String email;
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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

    public static User of(
            String email,
            String password,
            String nickName
    ) {
        return new User(email, password, nickName, UserRole.USER);
    }

    public void update(String nickName, String password) {
        Optional.ofNullable(nickName)
                .ifPresent(value -> this.nickName = value);
        Optional.ofNullable(password)
                .ifPresent(value -> this.password = value);
    }
}