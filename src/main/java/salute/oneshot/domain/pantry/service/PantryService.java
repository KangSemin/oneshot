package salute.oneshot.domain.pantry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.pantry.repository.PantryRepository;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryRepository pantryRepository;
}
