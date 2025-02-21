package salute.oneshot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.address.repository.AddressRepository;
import salute.oneshot.domain.auth.repository.RedisBlacklistRepository;
import salute.oneshot.domain.auth.repository.RedisRefreshTokenRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.dto.response.UserResponseDto;
import salute.oneshot.domain.user.dto.response.UserRoleResponseDto;
import salute.oneshot.domain.user.dto.service.DeleteUserSDto;
import salute.oneshot.domain.user.dto.service.UpdateRoleSDto;
import salute.oneshot.domain.user.dto.service.UpdateUserSDto;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RedisRefreshTokenRepository refreshTokenRepository;
    private final RedisBlacklistRepository blacklistRepository;

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
    public Long deleteUser(DeleteUserSDto serviceDto) {
        Long userId = serviceDto.getUserId();
        if (userRepository.softDelete(userId) == 1) {
            addressRepository.deleteAllByUserId(userId);

            String token = jwtProvider.extractToken(serviceDto.getToken());
            refreshTokenRepository.delete(userId);
            blacklistRepository.save(
                    token,
                    jwtProvider.getRemainMilliSeconds(token));

            return userId;
        }
        throw new ConflictException(ErrorCode.DUPLICATE_USER_DELETE);
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
