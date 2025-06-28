package salute.oneshot.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CookieUtil {

    public Map<String, Cookie> FindOrElseCreateCookie(List<String> cookieNameList, HttpServletRequest servletRequest) {

        List<Cookie> cookieArr = List.of(servletRequest.getCookies());
        Map<String, Cookie> cookieMap = new HashMap<>();

        for (String cookieName : cookieNameList) {
            Cookie targetCookie = cookieArr.stream()
                    .filter(c -> c.getName().equals(cookieName)).findFirst().get();

            cookieMap.put(cookieName, targetCookie);
        }
        return cookieMap;
    }
}
