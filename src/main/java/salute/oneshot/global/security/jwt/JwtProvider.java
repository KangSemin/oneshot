package salute.oneshot.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import salute.oneshot.global.security.SecurityConst;
import salute.oneshot.global.security.entity.CustomUserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final SecretKey secretKey;
    private final JwtValidator jwtValidator;

    public Claims parseClaims(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        validateToken(claims);
        return claims;
    }

    public String createToken(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Date now = new Date();

        return Jwts.builder()
                .subject(principal.getId().toString())
                .claim("email", principal.getUsername())
                .claim("role", principal.getRole().name())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + SecurityConst.TOKEN_TIME))
                .signWith(secretKey)
                .compact();
    }

    private void validateToken(Claims claims) {
        Long userId = Long.parseLong(claims.getSubject());
        Date issuedAt = claims.getIssuedAt();

        jwtValidator.validateToken(userId, issuedAt);
    }
}
