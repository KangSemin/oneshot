package salute.oneshot.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
