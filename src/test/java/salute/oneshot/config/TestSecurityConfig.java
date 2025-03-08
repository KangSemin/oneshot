package salute.oneshot.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 이 부분이 중요합니다!
                .securityContext(securityContext -> securityContext
                        .securityContextRepository(new DelegatingSecurityContextRepository(
                                new HttpSessionSecurityContextRepository(),
                                new RequestAttributeSecurityContextRepository())));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails testUser = User.withUsername("testuser")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(testUser);
    }
}
