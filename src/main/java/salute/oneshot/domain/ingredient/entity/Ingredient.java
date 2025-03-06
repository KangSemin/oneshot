package salute.oneshot.domain.ingredient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

@Entity
@Table(name = "ingredients")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    private String name;
    private Double avb;
    private String description;

    @Enumerated(EnumType.STRING)
    private IngredientCategory category;

    private String imageUrl;


    public void update(String name, String description, IngredientCategory category, Double avb) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.avb = avb;
    }


    private Ingredient(String name, String description, IngredientCategory category, Double avb, String imageUrl) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.avb = avb;
        this.imageUrl = imageUrl;
    }

    public static Ingredient of(String name, String description,
        IngredientCategory category, Double avb, String imageUrl) {
        return new Ingredient(name, description, category, avb, imageUrl);
    }
}
