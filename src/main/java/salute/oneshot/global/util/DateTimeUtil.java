package salute.oneshot.global.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.global.exception.InvalidException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@Slf4j
@UtilityClass
public class DateTimeUtil {

    public LocalDateTime parseStartDateTime(String date, String time) {
        return parseDateTime(date, time, LocalTime.MIN);
    }

    public LocalDateTime parseEndDateTime(String date, String time) {
        return parseDateTime(date, time, LocalTime.MAX);
    }

    private LocalDateTime parseDateTime(String date, String time, LocalTime localTime) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            LocalTime parsedTime = time != null && !time.isEmpty() ?
                    LocalTime.parse(time) :
                    localTime;
            return LocalDateTime.of(parsedDate, parsedTime);
        } catch (DateTimeParseException e) {
            log.error("유효하지 않은 형식: {}, {}", date, time );
            throw new InvalidException(ErrorCode.INVALID_DATETIME);
        }
    }
}
