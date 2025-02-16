package salute.oneshot.domain.pantry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.ingredient.entity.Ingredient;

@Entity
@Table(name = "pantries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pantry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Pantry(Long userId, Ingredient ingredient) {
        this.userId = userId;
        this.ingredient = ingredient;
    }

    public static Pantry of(Long userId, Ingredient ingredient) {
        return new Pantry(userId, ingredient);
    }

}
