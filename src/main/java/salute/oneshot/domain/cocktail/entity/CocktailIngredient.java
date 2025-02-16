package salute.oneshot.domain.cocktail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.ingredient.entity.Ingredient;

@Getter
@Entity
@Table(name = "Cocktail_Ingredients")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CocktailIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private String volume;


    private CocktailIngredient(Cocktail cocktail, Ingredient ingredient, String volume) {
        this.cocktail = cocktail;
        this.ingredient = ingredient;
        this.volume = volume;
    }

    public static CocktailIngredient of(Cocktail cocktail, Ingredient ingredient, String volume) {
        return new CocktailIngredient(cocktail, ingredient, volume);
    }
}

