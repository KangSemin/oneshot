package salute.oneshot.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestIdentifierManager {

    private final CookieUtil cookieUtil;
    private final HashMacUtil hashMacUtil;

    private final String UUID_COOKIE = "uuid";
    private final String SIGNATURE_COOKIE = "signature";
    private final BigInteger MAX_OFFSET = BigInteger.valueOf(4_294_967_296L);

    public Long FindOrElseCreateKey(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Map<String,Cookie> cookieMap = cookieUtil.findOrElseCreateCookie(List.of(UUID_COOKIE, SIGNATURE_COOKIE), servletRequest);
        Cookie uuidCookie = cookieMap.get(UUID_COOKIE);
        Cookie signatureCookie = cookieMap.get(SIGNATURE_COOKIE);

        if (uuidCookie == null && signatureCookie == null) {

            String uuid = BigInteger.valueOf(UUID.randomUUID().getMostSignificantBits()).mod(MAX_OFFSET).toString();
            uuidCookie = new Cookie(UUID_COOKIE, uuid);

            String signature = hashMacUtil.createSignature(uuid);
            signatureCookie = new Cookie(SIGNATURE_COOKIE, signature);

            servletResponse.addCookie(uuidCookie);
            servletResponse.addCookie(signatureCookie);

            return Long.parseLong(uuidCookie.getValue());
        }

        return  hashMacUtil.validValue(uuidCookie.getValue(), signatureCookie.getValue()) ? Long.parseLong(uuidCookie.getValue()) : null ;
    }
}
