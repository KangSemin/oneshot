package salute.oneshot.domain.banner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.banner.entity.BannerValidationConst;
import salute.oneshot.domain.common.entity.ValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannerRequestDto {

    @NotNull(message = BannerValidationConst.EVENT_ID_BLANK_MESSAGE)
    private final Long eventId;

    @NotBlank(message = BannerValidationConst.IMAGE_URL_ID_BLANK_MESSAGE)
    private final String imageUrl;

    @NotBlank(message = ValidationConst.DATE_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.DATE_REG,
            message = ValidationConst.DATE_TYPE_MESSAGE)
    private final String startDate;

    @NotBlank(message = ValidationConst.TIME_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.TIME_REG,
            message = ValidationConst.TIME_TYPE_MESSAGE)
    private final String startTime;

    @NotBlank(message = ValidationConst.DATE_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.DATE_REG,
            message = ValidationConst.DATE_TYPE_MESSAGE)
    private final String endDate;

    @NotBlank(message = ValidationConst.TIME_BLANK_MESSAGE)
    @Pattern(
            regexp = ValidationConst.TIME_REG,
            message = ValidationConst.TIME_TYPE_MESSAGE)
    private final String endTime;

    public static BannerRequestDto of(
            Long bannerId,
            String imageUrl,
            String startDate,
            String startTime,
            String endDate,
            String endTime
    ) {
        return new BannerRequestDto(
                bannerId,
                imageUrl,
                startDate,
                startTime,
                endDate,
                endTime);
    }
}
