package salute.oneshot.domain.event.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.cocktail.entity.Cocktail;

@Entity
    @Table(name = "cocktail_candidate")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class VoteEventCandidate {

        @Id @GeneratedValue
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        private Cocktail cocktail;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "event_id")
        private Event event;

        private int voteCount = 0;
}