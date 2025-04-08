package salute.oneshot.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CookieUtil {

    public static Cookie[] getCookies(HttpServletRequest request){
        return request.getCookies();
    }


    public static Cookie getOrCreateCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();

        String ip = request.getHeader("X-Forwarded-For").split(",")[0];
        String agent = request.getHeader("User-Agent");
        String value = " ";

        if (cookies == null) {
            return new Cookie(cookieName, value);//여기에다가
        }// 이부분이랑 코드가 너무 겹침

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName)).findFirst();

        if (optionalCookie.isEmpty()) {
            return new Cookie(cookieName, value);
        }// 이 부분이 확실히 짜치긴한다.

        return optionalCookie.get();
    }


    public static void setCookieTime(Cookie cookie){
        long todayEndTime = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/");
        cookie.setMaxAge((int) (todayEndTime - currentTime));
    }
}
