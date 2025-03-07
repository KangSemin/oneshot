package salute.oneshot.domain.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salute.oneshot.domain.address.entity.Address;
import salute.oneshot.domain.address.service.AddressService;
import salute.oneshot.global.security.model.CustomUserDetails;

@Controller
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressViewController {

    private final AddressService addressService;

    @GetMapping
    public String addressPage() {

        return "address/address-create";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{addressId}/edit")
    public String addressEditPage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long addressId,
            Model model
    ) {
        Address address = addressService.getAddressByIdAndUserId(
                addressId, userDetails.getId());
        model.addAttribute("address", address);

        return "address/address-update";
    }
}
