package salute.oneshot.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.ConflictException;

import java.time.LocalDateTime;

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

    public static User of(
            String email,
            String password,
            String nickName
    ) {
        return new User(email, password, nickName, UserRole.USER);
    }

    public void update(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public void softDelete() {
        if (this.isDeleted) {
            throw new ConflictException(ErrorCode.DUPLICATE_USER_DELETE);
        }
        this.isDeleted = true;
        this.isDeletedAt = LocalDateTime.now();
    }
}
