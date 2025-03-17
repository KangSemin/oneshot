package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(nullable = false, name = "limit_count")
    private int limitCount;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private EventDetail eventDetail;

    private Event(
            String name,
            String description,
            LocalDateTime startTime,
            LocalDateTime endTime,
            EventType eventType,
            int limitCount
    ) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventType = eventType;
        // 프론트 미존재여서 일단 온고잉으로
        this.status = EventStatus.ONGOING;
        this.limitCount = limitCount;
    }

    public static Event of(
            String name,
            String description,
            LocalDateTime startTime,
            LocalDateTime endTime,
            EventType eventType,
            int limitCount
    ) {
        return new Event(
                name,
                description,
                startTime,
                endTime,
                eventType,
                limitCount);
    }

    public void changeEventStatus(EventStatus status) {
        this.status = status;
    }

    public void addEventDetail(EventDetail eventDetail) {
        this.eventDetail = eventDetail;
    }

    public void updateEvent(
            String name,
            EventType eventType,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String eventDetailJson
    ) {
        this.name = name;
        this.eventType = eventType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventDetail.updateDetailData(eventDetailJson);
    }
}