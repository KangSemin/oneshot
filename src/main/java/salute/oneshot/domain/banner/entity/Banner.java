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

    public Banner(
            Event event,
            String imageUrl,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.event = event;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Banner of(
            Event event,
            String imageUrl,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        return new Banner(
                event,
                imageUrl,
                startTime,
                endTime);
    }

    public void updateBanner(
            Event event,
            String imageUrl,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.event = event;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this. endTime = endTime;
    }
}