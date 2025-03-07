package salute.oneshot.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import salute.oneshot.global.util.NonceGenerator;

@Component
@RequiredArgsConstructor
public class NonceInterceptor implements HandlerInterceptor {

    private final NonceGenerator nonceGenerator;
    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            ModelAndView modelAndView
    ) {
        if (modelAndView != null) {
            String nonce = (String) request.getAttribute("cspNonce");
            modelAndView.addObject("scriptNonce", nonce);
        }
    }
}
