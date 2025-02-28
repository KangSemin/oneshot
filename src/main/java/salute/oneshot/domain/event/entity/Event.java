package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

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

    @Column(nullable = false, name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private EventDetail eventDetail;

    private Event(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime,
            EventType eventType
    ) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventType = eventType;
        this.status = EventStatus.UPCOMING;
    }

    public static Event of(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime,
            EventType eventType
    ) {
        return new Event(name, startTime, endTime, eventType);
    }

    public void addEventDetail(EventDetail eventDetail) {
        this.eventDetail = eventDetail;
    }
}