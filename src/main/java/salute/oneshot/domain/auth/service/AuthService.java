package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.auth.dto.response.SignInResponseDto;
import salute.oneshot.domain.auth.dto.response.AuthResponseDto;
import salute.oneshot.domain.auth.dto.service.SignInSDto;
import salute.oneshot.domain.auth.dto.service.AuthSDto;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.InvalidException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.security.SecurityConst;
import salute.oneshot.global.security.jwt.JwtProvider;
import salute.oneshot.global.security.service.CustomUserDetailsService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;

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

    @Transactional(readOnly = true)
    public SignInResponseDto userSignIn(SignInSDto serviceDto) {
        User user = userRepository
                .findByEmailAndIsDeletedIsFalse(serviceDto.getEmail())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder
                .matches(serviceDto.getPassword(), user.getPassword())) {
            throw new InvalidException(ErrorCode.LOGIN_FAILED);
        }

        Authentication authentication =
                userDetailsService.createAuthentication(
                        user.getId(),
                        user.getEmail(),
                        user.getUserRole());

        String token = jwtProvider.createToken(authentication);

        return SignInResponseDto.of(token, SecurityConst.TOKEN_TYPE);
    }

    @Transactional
    public AuthResponseDto signOut(Long id) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.USER_NOT_FOUND));
        user.logout();

        return AuthResponseDto.from(user);
    }
}
