package salute.oneshot.util;

import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.event.dto.request.EventRequestDto;
import salute.oneshot.domain.event.entity.Event;
import salute.oneshot.domain.event.entity.EventDetail;
import salute.oneshot.domain.event.entity.EventStatus;
import salute.oneshot.domain.event.entity.EventType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class EventTestFactory {

    public static final Long EVENT_ID = 1L;
    public static final String NAME = "상품 증정 선착순 이벤트";
    public static final String DESCRIPTION = "선착순 10명에게 소정의 상품을 드립니다";
    public static final String START_DATE = "2025-03-08";
    public static final String START_TIME = "18:00";
    public static final String END_DATE = "2025-03-09";
    public static final String END_TIME = "19:00";
    public static final String EVENT_TYPE = "fcfs";
    public static final int LIMIT_COUNT = 10;
    public static final Object EVENT_DETAIL_DATA = Map.of(
            "couponId", 1L,
            "couponName", "20% 할인 쿠폰");
    public static final LocalDateTime START_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-08T18:00:00");
    public static final LocalDateTime END_LOCAL_DATE_TIME = LocalDateTime.parse("2025-03-09T19:00:00");

    public static Event createEvent() {
        Event event = Event.of(
                NAME,
                DESCRIPTION,
                START_LOCAL_DATE_TIME,
                END_LOCAL_DATE_TIME,
                EventType.of(EVENT_TYPE),
                LIMIT_COUNT);
        EventDetail eventDetail =
                EventDetail.of(event, EVENT_DETAIL_DATA.toString());
        event.addEventDetail(eventDetail);

        ReflectionTestUtils.setField(event, "id", EVENT_ID);
        return event;
    }

    public static Event createEvent2() {
        Event event = Event.of(
                NAME,
                DESCRIPTION,
                LocalDateTime.parse("2025-03-07T18:00:00"),
                LocalDateTime.parse("2025-03-09T18:00:00"),
                EventType.of("vote"),
                LIMIT_COUNT);
        EventDetail eventDetail =
                EventDetail.of(event, EVENT_DETAIL_DATA.toString());
        event.addEventDetail(eventDetail);

        ReflectionTestUtils.setField(event, "id", 2L);
        return event;
    }

    public static Event createEvent3() {
        Event event = Event.of(
                NAME,
                DESCRIPTION,
                LocalDateTime.parse("2025-03-09T18:00:00"),
                LocalDateTime.parse("2025-03-10T18:00:00"),
                EventType.of(EVENT_TYPE),
                LIMIT_COUNT);
        EventDetail eventDetail =
                EventDetail.of(event, EVENT_DETAIL_DATA.toString());
        event.addEventDetail(eventDetail);

        event.changeEventStatus(EventStatus.ONGOING);
        ReflectionTestUtils.setField(event, "id", 3L);
        return event;
    }

    public static EventRequestDto createEventRequestDto()
            throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(1);
        LocalDateTime endTime = now.plusDays(7);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return reflectEventRequestDto(
                NAME,
                DESCRIPTION,
                startTime.format(dateFormatter),
                startTime.format(timeFormatter),
                endTime.format(dateFormatter),
                endTime.format(timeFormatter),
                EVENT_TYPE,
                LIMIT_COUNT,
                EVENT_DETAIL_DATA);
    }

    public static EventRequestDto createInvalidEndEventRequestDto()
            throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(1);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return reflectEventRequestDto(
                NAME,
                DESCRIPTION,
                startTime.format(dateFormatter),
                startTime.format(timeFormatter),
                END_DATE,
                END_TIME,
                EVENT_TYPE,
                LIMIT_COUNT,
                EVENT_DETAIL_DATA);
    }

    public static EventRequestDto createInvalidStartEventRequestDto()
            throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(10);
        LocalDateTime endTime = now.plusDays(5);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return reflectEventRequestDto(
                NAME,
                DESCRIPTION,
                startTime.format(dateFormatter),
                startTime.format(timeFormatter),
                endTime.format(dateFormatter),
                endTime.format(timeFormatter),
                EVENT_TYPE,
                LIMIT_COUNT,
                EVENT_DETAIL_DATA);
    }

    public static EventRequestDto reflectEventRequestDto(String name, String description, String startDate, String startTime, String endDate, String endTime, String eventType, int limitCount, Object eventDetailDate)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Constructor<EventRequestDto> eventCont = EventRequestDto.class
                .getDeclaredConstructor(
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        String.class,
                        int.class,
                        Object.class);
        eventCont.setAccessible(true);

        return eventCont.newInstance(
                name,
                description,
                startDate,
                startTime,
                endDate,
                endTime,
                eventType,
                limitCount,
                eventDetailDate);
    }
}
