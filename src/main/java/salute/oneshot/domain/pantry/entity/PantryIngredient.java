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
@Table(name = "pantry_ingredients")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PantryIngredient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pantry_id")
    private Pantry pantry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private PantryIngredient(Pantry pantry, Ingredient ingredient) {
        this.pantry = pantry;
        this.ingredient = ingredient;
    }

    public static PantryIngredient of(Pantry pantry, Ingredient ingredient) {
        return new PantryIngredient(pantry, ingredient);
    }

}