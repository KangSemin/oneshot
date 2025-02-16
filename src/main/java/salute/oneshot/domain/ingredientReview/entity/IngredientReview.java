package salute.oneshot.domain.ingredientReview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.ingredient.entity.Ingredient;

@Entity
@Table(name = "ingredient_review")
public class IngredientReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "star")
    private Byte star;

    @ManyToOne
    @JoinColumn(name = "inredient_id")
    private Ingredient ingredient;
}
