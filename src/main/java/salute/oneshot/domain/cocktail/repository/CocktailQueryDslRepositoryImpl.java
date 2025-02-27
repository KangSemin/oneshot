package salute.oneshot.domain.cocktail.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.QCocktail;
import salute.oneshot.domain.cocktail.entity.QCocktailIngredient;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;

import static salute.oneshot.domain.cocktail.entity.QCocktail.cocktail;

@Repository
@RequiredArgsConstructor
public class CocktailQueryDslRepositoryImpl implements CocktailQueryDslRepository {

    private final JPAQueryFactory queryFactory;


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


    public void addViewCntFromRedis(Long cocktailId, Integer viewCnt){
       Integer addViewCnt = ( viewCnt == null ? 0 : viewCnt );

        queryFactory.update(cocktail)
                .set(cocktail.viewCount, cocktail.viewCount.add(addViewCnt))
                .where(cocktail.id.eq(cocktailId))
                .execute();
    }


    public void addFavoriteCntFromRedis(Long cocktailId, Integer favoriteCnt){
        Integer addFavoriteCnt = (favoriteCnt == null ? 0 : favoriteCnt);

        queryFactory.update(cocktail)
                .set(cocktail.favoriteCount, cocktail.favoriteCount.add(addFavoriteCnt))
                .where(cocktail.id.eq(cocktailId))
                .execute();
    }
}
