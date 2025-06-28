package salute.oneshot.global.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GuestIdentifierManager {

    private final CookieUtil cookieUtil;
    private final HashMacUtil hashMacUtil;

    final String UUID_COOKIE = "uuid";
    final String SIGNATURE_COOKIE = "signature";

    public String FindOrElseCreateKey(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Map<String,Cookie> cookieMap = cookieUtil.FindOrElseCreateCookie(List.of(UUID_COOKIE, SIGNATURE_COOKIE), servletRequest);
        Cookie uuidCookie = cookieMap.get(UUID_COOKIE);
        Cookie signatureCookie = cookieMap.get(SIGNATURE_COOKIE);

        if (uuidCookie == null || signatureCookie == null) {

            String uuid = String.valueOf(UUID.randomUUID().getMostSignificantBits());
            uuidCookie = new Cookie(UUID_COOKIE, uuid);

            String signature = hashMacUtil.createSignature(uuid);
            signatureCookie = new Cookie(SIGNATURE_COOKIE, signature);

            servletResponse.addCookie(uuidCookie);
            servletResponse.addCookie(signatureCookie);

            return uuidCookie.getValue();
        }

        return  hashMacUtil.validValue(uuidCookie.getValue(), signatureCookie.getValue()) ? uuidCookie.getValue() : null ;
    }
}
