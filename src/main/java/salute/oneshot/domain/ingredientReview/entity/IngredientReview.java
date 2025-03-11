package salute.oneshot.domain.ingredientReview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.entity.BaseEntity;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.user.entity.User;

@Entity
@Getter
@Table(name = "ingredient_review")
@NoArgsConstructor
public class IngredientReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private Byte star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "inredient_id")
    private Ingredient ingredient;

    private IngredientReview(Byte star, String content, User user, Ingredient ingredient) {
        this.star = star;
        this.content = content;
        this.user = user;
        this.ingredient = ingredient;
    }

    public static IngredientReview of(Byte star, String content, User user, Ingredient ingredient) {
        return new IngredientReview(star, content, user, ingredient);
    }

    public void updateIngredientReview(Byte star, String content) {
        this.star = star;
        this.content = content;
    }
}
