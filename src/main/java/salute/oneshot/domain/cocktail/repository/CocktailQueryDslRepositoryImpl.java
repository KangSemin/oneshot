package salute.oneshot.domain.cocktail.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.QCocktail;
import salute.oneshot.domain.cocktail.entity.QCocktailIngredient;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

import static salute.oneshot.domain.cocktail.entity.QCocktail.cocktail;

@Repository
public class CocktailQueryDslRepositoryImpl implements CocktailQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public CocktailQueryDslRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Cocktail> searchCocktailsByIngredients(List<Ingredient> selectedIngrs,
        Pageable pageable) {

        QCocktail cocktail = QCocktail.cocktail;
        QCocktailIngredient cocktailIngr = QCocktailIngredient.cocktailIngredient;

        BooleanBuilder builder = new BooleanBuilder();

        for (Ingredient ingr : selectedIngrs) {
            BooleanBuilder subCondition = new BooleanBuilder();

            if (ingr.getCategory() != IngredientCategory.OTHER) {
                subCondition.or(cocktailIngr.ingredient.id.eq(ingr.getId()))
                    .or(cocktailIngr.ingredient.name.eq(ingr.getCategory().name()));
            } else {
                subCondition.and(cocktailIngr.ingredient.id.eq(ingr.getId()));
            }

            builder.and(JPAExpressions.selectOne().from(cocktailIngr)
                .where(cocktailIngr.cocktail.id.eq(cocktail.id), subCondition).exists());

        }

        List<Cocktail> content = queryFactory.selectFrom(cocktail)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(cocktail.name.asc())
            .fetch();
        long total = queryFactory.selectFrom(cocktail).where(builder).stream().count();

        return new PageImpl<>(content, pageable, total);

    }

    public void addViewCntFromRedis(Long cocktailId, Integer viewCount){
       Integer addViewCount = (viewCount == null ? 0 : viewCount);

        queryFactory.update(cocktail)
                .set(cocktail.viewCount, cocktail.viewCount.add(addViewCount))
                .where(cocktail.id.eq(cocktailId))
                .execute();
    }
}
