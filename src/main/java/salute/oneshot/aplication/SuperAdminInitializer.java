package salute.oneshot.aplication;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class SuperAdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${super.username}")
    private String email;

    @Value("${super.nickName}")
    private String nickname;

    @Value("${super.password}")
    private String password;

    @PostConstruct
    public void createSuperAdmin() {
        UserRole role = UserRole.SUPER_ADMIN;

        if (!userRepository.existsByEmail(email)) {
            String encodedPassword =
                    passwordEncoder.encode(password);
            User superAdmin =
                    User.of(email, encodedPassword, nickname, role);
            userRepository.save(superAdmin);
        }
    }
}
