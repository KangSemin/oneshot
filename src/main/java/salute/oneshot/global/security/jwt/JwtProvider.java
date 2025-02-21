package salute.oneshot.global.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.domain.auth.repository.RedisBlacklistRepository;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.global.security.SecurityConst;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey secretKey;
    private final RedisBlacklistRepository blacklistCacheRepository;

    public String createAccessToken(Long userId, UserRole role) {
        Date now = new Date();
        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", role.name())
                .issuedAt(now)
                .expiration(new Date(now.getTime() +
                        SecurityConst.ACCESS_TTL))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(Long userId) {
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }

    public TokenInfo createToken(Long userId, UserRole role) {
        String accessToken =
                createAccessToken(userId, role);
        String refreshToken =
                createRefreshToken(userId);
        long accessExpiresAt = System.currentTimeMillis() +
                SecurityConst.ACCESS_TTL;

        return TokenInfo.of(
                accessToken,
                refreshToken,
                accessExpiresAt);
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }

    public String extractToken(String authorizationHeader) {
        if (!StringUtils.hasText(authorizationHeader) ||
                !authorizationHeader.startsWith(SecurityConst.BEARER_PREFIX)
        ) {
            log.warn("잘못된 토큰 형식 또는 Bearer 접두사 누락: {}", authorizationHeader);
            throw new JwtException(SecurityConst.INVALID_TOKEN);
        }
        return authorizationHeader.substring(SecurityConst.BEARER_PREFIX.length());
    }

    public void validateToken(String token) {
        if (blacklistCacheRepository.existsByToken (token)) {
            log.warn("블랙리스트에 등록된 토큰 : {}", token);
            throw new JwtException(SecurityConst.INVALID_TOKEN);
        }
    }

    public long getRemainMilliSeconds(String token) {
        Claims claims = parseClaims(token);
        long expirationTime = claims.getExpiration().getTime();
        long currentTime = System.currentTimeMillis();

        return Math.max(expirationTime - currentTime, 0);
    }
}
