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
import salute.oneshot.domain.auth.handler.OAuth2FailureHandler;
import salute.oneshot.domain.auth.handler.OAuth2SuccessHandler;
import salute.oneshot.domain.auth.service.CustomOAuth2UserService;
import salute.oneshot.global.security.model.SecurityConst;
import salute.oneshot.global.security.handler.JwtAccessDeniedHandler;
import salute.oneshot.global.security.handler.JwtAuthenticationEntryPoint;
import salute.oneshot.global.security.filter.JwtFilter;

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
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailureHandler oAuth2FailureHandler;

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
                                .contentSecurityPolicy(csp -> csp
                                        .policyDirectives(SecurityConst.CSP.buildFullPolicy("temp")))
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

                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
                        .successHandler(oAuth2SuccessHandler)
                        .failureHandler(oAuth2FailureHandler))

                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())

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
