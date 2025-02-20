package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.auth.dto.response.AuthResponseDto;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.dto.service.SignInSDto;
import salute.oneshot.domain.auth.dto.service.AuthSDto;
import salute.oneshot.domain.auth.dto.service.SignOutSDto;
import salute.oneshot.domain.auth.entity.RefreshToken;
import salute.oneshot.domain.auth.repository.RefreshTokenRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.event.TokenInvalidationEvent;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.jwt.JwtProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public AuthResponseDto userSignUp(AuthSDto serviceDto) {
        if (userRepository.existsByEmail(serviceDto.getEmail())) {
            throw new ConflictException(ErrorCode.DUPLICATE_EMAIL);
        }

        User user = User.of(
                serviceDto.getEmail(),
                passwordEncoder.encode(serviceDto.getPassword()),
                serviceDto.getNickName());
        userRepository.save(user);

        return AuthResponseDto.from(user);
    }

    @Transactional
    public TokenInfo userSignIn(SignInSDto serviceDto) {
        User user = userRepository
                .findByEmailAndIsDeletedIsFalse(serviceDto.getEmail())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder
                .matches(serviceDto.getPassword(), user.getPassword())) {
            throw new InvalidException(ErrorCode.LOGIN_FAILED);
        }

        TokenInfo tokenInfo = jwtProvider.createToken(user.getId());

        saveRefreshToken(user.getId(), tokenInfo);

        return tokenInfo;
    }

    @Transactional
    public AuthResponseDto signOut(SignOutSDto serviceDto) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(serviceDto.getId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        eventPublisher.publishEvent(TokenInvalidationEvent.of(
                jwtProvider.extractToken(serviceDto.getToken())));

        return AuthResponseDto.from(user);
    }

    public String reissueAccessToken(String refreshToken) {
        Long userId = jwtProvider.getUserIdFromToken(refreshToken);

        RefreshToken storedToken = refreshTokenRepository
                .findByUserId(userId)
                .orElseThrow(() -> new InvalidException(ErrorCode.INVALID_TOKEN));

        if (!storedToken.getToken().equals(refreshToken)) {
            throw new InvalidException(ErrorCode.INVALID_TOKEN);
        }

        return jwtProvider.createAccessToken(userId);
    }

    private void saveRefreshToken(Long userId, TokenInfo tokenInfo) {
        refreshTokenRepository.upsertRefreshToken(
                userId,
                tokenInfo.getRefreshToken(),
                tokenInfo.getRefreshExpiresAt());
    }
}
