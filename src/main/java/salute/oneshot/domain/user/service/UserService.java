package salute.oneshot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.UpdateUserSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(Long userId) {
        User user = getUserById(userId);

        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto updateUserInfo(UpdateUserSDto userUpdateSDto) {
        User user = getUserById(userUpdateSDto.getId());
        user.update(
                userUpdateSDto.getNickName(),
                passwordEncoder.encode(userUpdateSDto.getPassword())
        );

        return UserResponseDto.from(user);
    }

    @Transactional
    public UserResponseDto deleteUser(Long userId) {
        User user = getUserById(userId);

        addressRepository.deleteAllByUserId(userId);

        user.softDelete();
        user.logout();


        return UserResponseDto.from(user);
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
