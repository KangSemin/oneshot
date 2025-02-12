package salute.oneshot.domain.recipeReview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.recipe.entity.Recipe;
import salute.oneshot.domain.user.entity.User;


@Entity
@Getter
@Table(name = "recipe_review")
@NoArgsConstructor
public class RecipeReview extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private Byte star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private RecipeReview(String content, Byte star, Recipe recipe) {
        this.content = content;
        this.star = star;
        this.recipe = recipe;
    }

    public static RecipeReview of(String content, Byte star, Recipe recipe) {
        return new RecipeReview(content,star,recipe);
    }
}
