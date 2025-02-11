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


    private Ingredient(String name, Double avb, String description, IngredientCategory category) {
        this.name = name;
        this.avb = avb;
        this.description = description;
        this.category = category;
    }

    public static Ingredient of(String name, Double avb, String description,
        IngredientCategory category) {
        return new Ingredient(name, avb, description, category);
    }
}
