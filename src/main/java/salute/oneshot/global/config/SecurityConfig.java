package salute.oneshot.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import salute.oneshot.global.security.jwt.JwtAccessDeniedHandler;
import salute.oneshot.global.security.jwt.JwtAuthenticationEntryPoint;
import salute.oneshot.global.security.jwt.JwtFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors ->
                        cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers
                        // CSP: 웹 리소스 로딩 정책 설정
                        // - 모든 리소스는 같은 출처(self)에서만 로드 허용
                        // - XSS 방어 및 리소스 로딩 제어
                        // 추후 외부에서 리소스 가져와야할 때 변경필요
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +     // 기본적으로 모든 리소스는 같은 출처에서만
                                        "script-src 'self'; " +      // JavaScript 파일
                                        "style-src 'self'; " +       // CSS 파일
                                        "img-src 'self'; " +         // 이미지 파일
                                        "font-src 'self'; " +        // 폰트 파일
                                        "form-action 'self';"        // 폼 제출 대상
                                ))
                        // Clickjacking 방지: frame, iframe 에서 페이지 로드 차단
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)

                        // TODO: HTTPS 전환 후 HSTS 설정 활성화 필요
                        // HSTS: HTTPS 강제 설정
                        // - 브라우저가 HTTP 대신 HTTPS만 사용하도록 강제
                        // - includeSubDomains: 모든 서브도메인에도 적용
                        // - maxAge: 1년 동안 HSTS 적용
                        // - preload: 브라우저 HSTS 프리로드 목록에 등록 가능하도록 설정
//                        .httpStrictTransportSecurity(hsts -> hsts
//                                .includeSubDomains(true)
//                                .maxAgeInSeconds(31536000)
//                                .preload(true))
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/addresses/**").permitAll()
                        .requestMatchers("/payments/**").permitAll()
                        .requestMatchers("/orders/**").permitAll()
                        .anyRequest().authenticated())

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling(handler ->
                        handler.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
