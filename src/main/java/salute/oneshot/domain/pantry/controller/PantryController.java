package salute.oneshot.domain.pantry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import salute.oneshot.domain.pantry.service.PantryService;

@RestController
@RequestMapping("/api/pantries")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;
}
