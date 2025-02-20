package salute.oneshot.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import salute.oneshot.domain.auth.dto.response.TokenInfo;
import salute.oneshot.global.security.SecurityConst;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey secretKey;
    private final JwtValidator jwtValidator;

    public TokenInfo createToken(Long userId) {
        String accessToken =
                createAccessToken(userId);
        long accessExpiresAt =
                System.currentTimeMillis() + SecurityConst.ACCESS_TOKEN_EXPIRE_TIME;
        String refreshToken =
                createRefreshToken(userId);
        long refreshExpiresAt =
                System.currentTimeMillis() + SecurityConst.REFRESH_TOKEN_EXPIRE_TIME;
        return TokenInfo.of(accessToken, accessExpiresAt, refreshToken, refreshExpiresAt);
    }

    public String createAccessToken(Long userId) {
        return createToken(SecurityConst.ACCESS_TOKEN_EXPIRE_TIME, userId);
    }

    public String createRefreshToken(Long userId) {
        return createToken(SecurityConst.REFRESH_TOKEN_EXPIRE_TIME, userId);
    }

    private String createToken(long expireTime, Long userId) {
        Date now = new Date();

        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expireTime))
                .signWith(secretKey)
                .compact();
    }

    public Claims parseClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        validateToken(claims, token);
        return claims;
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
                !authorizationHeader.startsWith(SecurityConst.BEARER_PREFIX)) {
            throw new MalformedJwtException(SecurityConst.MALFORMED_TOKEN);
        }
        return authorizationHeader.substring(SecurityConst.BEARER_PREFIX.length());
    }

    private void validateToken(Claims claims, String token) {
        Long userId = Long.parseLong(claims.getSubject());
        Date issuedAt = claims.getIssuedAt();

        jwtValidator.validateToken(userId, issuedAt, token);
    }
}
