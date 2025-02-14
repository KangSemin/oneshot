package salute.oneshot.domain.address.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addresses")
public class AddressViewController {

    @GetMapping
    public String addressPage() {
        return "address";
    }
}
