package salute.oneshot.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CookieUtil {

    public static Cookie getOrCreateCookie(HttpServletRequest request, String cookieName)  {

        Cookie[] cookies = request.getCookies();

        String ip = HttpHeaderUtil.getClientIp(request);
        String agent = request.getHeader("User-Agent");
        String value = HashUtil.sha256(ip + agent).substring(0, 30);

        Cookie cookie = Optional.ofNullable(cookies)
                .flatMap(arr -> Arrays.stream(arr)
                        .filter(c -> c.getName().equals(cookieName))
                        .findFirst())
                .orElseGet(() -> new Cookie(cookieName, value));

        return cookie;
    }


    public static void setCookieTime(Cookie cookie){
        long todayEndTime = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/");
        cookie.setMaxAge((int) (todayEndTime - currentTime));
    }
}
