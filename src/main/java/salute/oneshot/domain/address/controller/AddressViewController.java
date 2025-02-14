package salute.oneshot.domain.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.service.AddressService;

@Controller
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressViewController {

    private final AddressService addressService;

    @GetMapping
    public String addressPage() {
        return "address-form";
    }

    @GetMapping("/{id}/edit")
    public String addressEditPage(@PathVariable Long id, Model model) {
        Address address = addressService.getAddressById(id);

        model.addAttribute("address", address);

        return "address-edit";
    }
}
