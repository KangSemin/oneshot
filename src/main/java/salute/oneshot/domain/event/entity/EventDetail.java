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
    private String detailDataJson;

    private EventDetail(
            Event event,
            String detailDataJson) {
        this.event = event;
        this.detailDataJson = detailDataJson;
    }

    public static EventDetail of(Event event, String detailDataJson) {
        return new EventDetail(event, detailDataJson);
    }

    public void updateDetailData(String detailDataJson) {
        this.detailDataJson = detailDataJson;
    }
}