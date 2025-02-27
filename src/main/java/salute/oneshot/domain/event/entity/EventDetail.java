package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_datails")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(columnDefinition = "TEXT")
    private String detailData;    // JSON 형태로 저장

    private EventDetail(
            Event event,
            String detailData) {
        this.event = event;
        this.detailData = detailData;
    }

    public static EventDetail of(Event event, String detailData) {
        return new EventDetail(event, detailData);
    }
}