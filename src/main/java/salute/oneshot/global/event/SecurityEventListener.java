package salute.oneshot.global.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import salute.oneshot.domain.auth.repository.BlacklistCacheRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityEventListener {

    private final BlacklistCacheRepository blacklistCacheRepository;

    @EventListener
    public void onTokenInvalidation(TokenInvalidationEvent event) {
        log.debug("토큰 무효화 이벤트 발생");
        blacklistCacheRepository.save(event.getToken(), event.getExpirationTime());
    }
}
