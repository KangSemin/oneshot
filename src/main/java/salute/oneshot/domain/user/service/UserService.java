package salute.oneshot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.UserUpdateSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto updateUserInfo(UserUpdateSDto userUpdateSDto) {
        User user = userRepository.findById(userUpdateSDto.getId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        user.update(
                userUpdateSDto.getNickName(),
                passwordEncoder.encode(userUpdateSDto.getPassword())
        );
        return UserResponseDto.from(user);
    }
}
