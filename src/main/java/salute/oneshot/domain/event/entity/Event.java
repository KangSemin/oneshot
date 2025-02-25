package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.event.dto.service.UpdateEventSDto;
import salute.oneshot.domain.eventcoupon.entity.EventCoupon;

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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventCoupon> eventCoupons = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreeEventWinner> winners = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteEventCandidate> candidates = new ArrayList<>();

    public Event(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = EventStatus.UPCOMING;
        this.eventCoupons = new ArrayList<>();
        this.winners = new ArrayList<>();
        this.candidates = new ArrayList<>();
    }

    public static Event of(
            String name,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        return new Event(name, startTime, endTime);
    }

    public void addEventCoupon(EventCoupon eventCoupon) {
        eventCoupons.add(eventCoupon);
    }

    public void addWinners(FreeEventWinner winner) {
        winners.add(winner);
    }

    public void addCandidates(VoteEventCandidate candidate) {
        candidates.add(candidate);
    }

    public void update(UpdateEventSDto serviceDto) {
        this.name = serviceDto.getName();
        this.startTime = serviceDto.getStartTime();
        this.endTime = serviceDto.getEndTime();
    }
}
