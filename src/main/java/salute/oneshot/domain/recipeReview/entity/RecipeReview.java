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
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
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
    @JoinColumn(name = "cocktail_id")
    private Cocktail cocktail;

    private RecipeReview(Byte star, String content, User user, Cocktail cocktail) {
        this.star = star;
        this.content = content;
        this.user = user;
        this.cocktail = cocktail;
    }

    public static RecipeReview of(Byte star, String content, User user, Cocktail cocktail) {
        return new RecipeReview(star,content,user,cocktail);
    }

    public void updateRecipeReview(Byte star, String content) {
        this.star = star;
        this.content = content;
    }
}
