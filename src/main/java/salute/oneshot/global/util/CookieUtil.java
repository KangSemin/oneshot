package salute.oneshot.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CookieUtil {

    public Map<String, Cookie> findOrElseCreateCookie(List<String> cookieNameList, HttpServletRequest servletRequest) {

        Map<String, Cookie> cookieMap = new HashMap<>();

        if(servletRequest.getCookies() == null){return cookieMap;}
        List<Cookie> cookieArr = List.of(servletRequest.getCookies());

        for (String cookieName : cookieNameList) {
            Cookie targetCookie = cookieArr.stream()
                    .filter(c -> c.getName().equals(cookieName)).findFirst().get();

            cookieMap.put(cookieName, targetCookie);
        }
        return cookieMap;
    }
}
