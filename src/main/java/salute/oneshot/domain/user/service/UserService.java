package salute.oneshot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.auth.repository.RefreshTokenRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.service.DeleteUserSDto;
import salute.oneshot.domain.user.dto.service.UpdateUserSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.event.TokenInvalidationEvent;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

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
    public UserResponseDto deleteUser(DeleteUserSDto serviceDto) {
        User user = getUserById(serviceDto.getUserId());

        addressRepository.deleteAllByUserId(serviceDto.getUserId());

        eventPublisher.publishEvent(TokenInvalidationEvent.of(
                jwtProvider.extractToken(serviceDto.getToken())));

        refreshTokenRepository.deleteById(serviceDto.getUserId());

        user.softDelete();

        return UserResponseDto.from(user);
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
