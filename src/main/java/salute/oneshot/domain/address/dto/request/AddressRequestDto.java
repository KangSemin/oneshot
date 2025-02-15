package salute.oneshot.domain.address.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressRequestDto {

    @NotBlank
    private String addressName;
    @NotBlank
    private String postcode;
    @NotBlank
    private String postAddress;
    @NotBlank
    private String detailAddress;
    private String extraAddress;
}
