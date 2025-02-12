package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto userSignUp(SignUpSDto signUpSDto) {
        if (userRepository.existsByEmail(signUpSDto.getEmail())) {
            throw new ConflictException(ErrorCode.DUPLICATE_EMAIL);
        }

        User user = User.of(
                signUpSDto.getEmail(),
                passwordEncoder.encode(signUpSDto.getPassword()),
                signUpSDto.getNickName()
        );

        userRepository.save(user);

        return SignUpResponseDto.from(user);
    }
}
