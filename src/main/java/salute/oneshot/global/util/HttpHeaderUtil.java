package salute.oneshot.global.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class HttpHeaderUtil {

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.isEmpty()){// 아이피가 널이 아니거나, 아이피의 길이가 0이 아니면
            return ip.split(",")[0];
        }

        String[] ipNames = new String[]{"Proxy-Client-IP", "WL-Proxy-Client-IP",
                                        "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

        for(String ipName : ipNames){
            if(request.getHeader(ipName) != null && !request.getHeader(ipName).isEmpty()){
                ip = request.getHeader(ipName);
                break;
            }
        }
        return ip;
    }

    public static boolean checkReferer(String[] whiteUrl, HttpServletRequest request){
        String referer = request.getHeader("Referer");

        for(String url : whiteUrl){
            if(referer.contains(url)){return true;}
        }
        return false;
    }
}
