package salute.oneshot.global.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import salute.oneshot.global.security.model.SecurityConst;
import salute.oneshot.global.util.NonceGenerator;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CspNonceFilter extends OncePerRequestFilter {

    private final NonceGenerator nonceGenerator;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String nonce = nonceGenerator.getNonce();

        request.setAttribute("cspNonce", nonce);

        response.setHeader("Content-Security-Policy",
                SecurityConst.CSP.buildFullPolicy(nonce));

        filterChain.doFilter(request, response);
    }
}
