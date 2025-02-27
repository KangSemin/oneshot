package salute.oneshot.domain.cocktail.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import salute.oneshot.domain.common.dto.entity.BaseEntity;
import salute.oneshot.domain.user.entity.User;

@Entity
@Table(name = "cocktails")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cocktail extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String name;
    private String description;
    private String recipe;

    @Enumerated(EnumType.STRING)
    private RecipeType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cocktail",cascade = CascadeType.REMOVE)
    private List<CocktailIngredient> ingredientList;

    private Integer likeCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Double starRate = 0.0;

    @ColumnDefault("0")
    private Integer viewCount = 0;


    private Cocktail(String name, String description, String recipe, RecipeType type,
        User user, List<CocktailIngredient> ingredientList) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.type = type;
        this.user = user;
        this.ingredientList = ingredientList;
    }

    public static Cocktail of(String name, String description, String recipe, RecipeType type,
        User user, List<CocktailIngredient> ingredientList) {
        return new Cocktail(name, description, recipe, type, user, ingredientList);
    }

    public void update(String name, String description, String recipe,
        List<CocktailIngredient> ingredientList) {
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.ingredientList = ingredientList;
    }
    public void incrementCount() {
        this.viewCount++;
    }
}
