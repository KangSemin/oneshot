package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private EventStatus status;

    @Column(name = "coupon_ids")
    private String couponIds;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreeEventWinner> winners = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteEventCandidate> candidates = new ArrayList<>();

    private Event(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String couponIds
    ) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = EventStatus.UPCOMING;
        this.couponIds = couponIds;
        this.winners = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public static Event of(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String couponIds
    ) {
        return new Event(name, startTime, endTime, couponIds);
    }
}
