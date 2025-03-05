package salute.oneshot.domain.banner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.event.entity.Event;

import java.time.LocalDateTime;

@Entity
@Table(name = "banners")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    private Banner(Event event, String imageUrl) {
        this.event = event;
        this.imageUrl = imageUrl;
        this.startTime = event.getStartTime().minusDays(7);
        this.endTime = event.getEndTime();
    }

    public static Banner of(Event event, String imageUrl) {
        return new Banner(event, imageUrl);
    }

    public void updateBanner(
            Event event,
            String imageUrl
    ) {
        this.event = event;
        this.imageUrl = imageUrl;
    }

    public void syncWithEventTime() {
        this.startTime = event.getStartTime().minusDays(7);
        this.endTime = event.getEndTime();
    }
}