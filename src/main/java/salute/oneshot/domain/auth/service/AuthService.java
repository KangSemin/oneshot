package salute.oneshot.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;
}
