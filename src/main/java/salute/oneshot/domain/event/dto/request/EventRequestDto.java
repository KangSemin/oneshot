package salute.oneshot.domain.event.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import salute.oneshot.domain.common.entity.ValidationConst;
import salute.oneshot.domain.event.entity.EventType;
import salute.oneshot.domain.event.entity.EventValidationConst;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventRequestDto {

    @NotBlank(message = EventValidationConst.NAME_MESSAGE)
    private final String name;

    @NotBlank(message = EventValidationConst.DESCRIPTION_MESSAGE)
    private final String description;

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

    @NotBlank(message = EventValidationConst.EVENT_TYPE_MESSAGE)
    private final String eventType;

    @NotNull(message = EventValidationConst.LIMIT_COUNT_MESSAGE)
    @Positive(message = EventValidationConst.LIMIT_COUNT_POSITIVE)
    private final int limitCount;

    @NotNull(message = EventValidationConst.EVENT_DETAIL_DATE_MESSAGE)
    private final Object eventDetailData;
}