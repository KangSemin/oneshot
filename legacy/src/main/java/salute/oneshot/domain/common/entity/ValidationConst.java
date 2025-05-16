package salute.oneshot.domain.common.entity;

public class ValidationConst {
    public static final String DATE_BLANK_MESSAGE = "날짜를 입력해 주세요.";
    public static final String DATE_TYPE_MESSAGE = "날짜 형식은 YYYY-MM-DD 형태여야 합니다";
    public static final String DATE_REG =  "\\d{4}-\\d{2}-\\d{2}";

    public static final String TIME_BLANK_MESSAGE = "시간을 입력해 주세요.";
    public static final String TIME_TYPE_MESSAGE = "시간 형식은 HH:MM 형태여야 합니다";
    public static final String TIME_REG = "\\d{2}:\\d{2}";
}
