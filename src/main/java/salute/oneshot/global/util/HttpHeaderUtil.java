package salute.oneshot.global.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HttpHeaderUtil {

    /*
        -사용자의 요청이 프록시 서버를 타고 서버에 도착 했다면,
        X-Forwarded-For에 클라이언트 ip, 프록시 ip가 차례대로 저장되기에 첫번째 ip를 가져온다.
        -다양한 경로를 통해 들어온 요청들의 우선순위 ip부터 차례대로 확인해서 리턴한다
        -모두 널이라면 서버로 바로 요청을 보냈을때 생성되는 ip를 반환한다
     */

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");

        if (ip != null && !ip.isEmpty()){
            return ip.split(",")[0];
        }

        String[] ipNames = new String[]{"Proxy-Client-IP", "WL-Proxy-Client-IP",
                                        "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

        for(String ipName : ipNames){
            if(request.getHeader(ipName) != null && !request.getHeader(ipName).isEmpty()){
                return request.getHeader(ipName);
            }
        }
        return request.getRemoteAddr();
    }

    /*
        정상적인 접근 경로를 여러개 담아두고, 사용자가 정상적인 접근 경로를 통해서 해당 페이지에 접근했는지 확인
     */

    public static boolean checkReferer(String[] validReferers, HttpServletRequest request){
        String referer = request.getHeader("Referer");

        for(String url : validReferers){
            if(url.contains(referer)){return true;}
        }
        if(referer == null){return true;}
        log.info("referer : " + referer);

        return false;
    }
}
