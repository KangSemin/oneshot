package salute.oneshot.domain.favorite.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

@Entity
@Table(name = "favorites", indexes = {
        @Index(name = "idx_favorite_user_cocktail", columnList = "user_id, cocktail_id"),
        @Index(name = "idx_favorite_user", columnList = "user_id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BigInt")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    public Favorite(Long userId, Cocktail cocktail) {
        this.userId = userId;
        this.cocktail = cocktail;
    }

    public static Favorite from(Long userId, Cocktail cocktail) {
        return new Favorite(userId, cocktail);
    }
}

