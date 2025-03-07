package salute.oneshot.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import salute.oneshot.global.interceptor.NonceInterceptor;
import salute.oneshot.global.util.NonceGenerator;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final NonceGenerator nonceGenerator;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new NonceInterceptor(nonceGenerator))
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}