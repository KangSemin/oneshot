package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.auth.dto.response.SignUpResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.LogInSDto;
import salute.oneshot.domain.auth.dto.service.SignUpSDto;
import salute.oneshot.domain.auth.dto.service.LogOutSDto;
import salute.oneshot.domain.auth.repository.RedisBlacklistRepository;
import salute.oneshot.domain.auth.repository.RedisRefreshTokenRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RedisRefreshTokenRepository refreshTokenRepository;
    private final RedisBlacklistRepository blacklistRepository;

    @Transactional
    public SignUpResponseDto userSignUp(SignUpSDto serviceDto) {
        if (userRepository.existsByEmail(serviceDto.getEmail())) {
            throw new ConflictException(ErrorCode.DUPLICATE_EMAIL);
        }

        User user = User.of(
                serviceDto.getEmail(),
                passwordEncoder.encode(serviceDto.getPassword()),
                serviceDto.getNickName());
        userRepository.save(user);

        return SignUpResponseDto.from(user);
    }

    @Transactional
    public TokenInfo logIn(LogInSDto serviceDto) {
        User user = userRepository
                .findByEmailAndIsDeletedIsFalse(serviceDto.getEmail())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder
                .matches(serviceDto.getPassword(), user.getPassword())) {
            throw new InvalidException(ErrorCode.LOGIN_FAILED);
        }

        TokenInfo tokenInfo =
                jwtProvider.createToken(user.getId(), user.getUserRole());
        refreshTokenRepository.save(
                user.getId(),
                user.getUserRole(),
                tokenInfo.getRefreshToken());

        return tokenInfo;
    }

    @Transactional
    public void logOut(LogOutSDto serviceDto) {
        if (!userRepository.existsUserById(serviceDto.getId())) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        String token = jwtProvider
                .extractToken(serviceDto.getToken());
        long remainMilliSeconds = jwtProvider
                .getRemainMilliSeconds(token);
        blacklistRepository.save(token, remainMilliSeconds);
    }

    @Transactional
    public TokenInfo refreshAccessToken(String refreshToken) {
        Long userId = jwtProvider.getUserIdFromToken(refreshToken);

        if (refreshTokenRepository.validate(userId, refreshToken)) {
            UserRole userRole = refreshTokenRepository.findRoleById(userId);

            TokenInfo tokenInfo = jwtProvider.createToken(userId, userRole);
            refreshTokenRepository.save(
                    userId,
                    userRole,
                    tokenInfo.getRefreshToken());

            return tokenInfo;
        }
        throw new InvalidException(ErrorCode.INVALID_TOKEN);
    }
}
