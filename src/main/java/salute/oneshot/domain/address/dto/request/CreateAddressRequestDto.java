package salute.oneshot.domain.address.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAddressRequestDto {
    // 카카오 API 주소정보를 받아오므로 @NoBlank 만 적용
    @NotBlank
    private final String addressName;
    @NotBlank
    private final String postcode;
    @NotBlank
    private final String postAddress;
    @NotBlank
    private final String detailAddress;
    private final String extraAddress;
}
