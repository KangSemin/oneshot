package salute.oneshot.domain.pantry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import salute.oneshot.domain.common.dto.entity.BaseEntity;

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

    @OneToMany(mappedBy = "pantry", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PantryIngredient> pantryIngredientList;

    private Pantry(Long userId) {
        this.pantryIngredientList = new ArrayList<>();
        this.userId = userId;
    }

    public static Pantry of(Long userId) {
        return new Pantry(userId);
    }

}
