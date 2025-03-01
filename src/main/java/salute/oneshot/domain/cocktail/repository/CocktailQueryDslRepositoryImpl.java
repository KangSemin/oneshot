package salute.oneshot.domain.cocktail.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.entity.QCocktail;
import salute.oneshot.domain.cocktail.entity.QCocktailIngredient;
import salute.oneshot.domain.ingredient.entity.Ingredient;
import salute.oneshot.domain.ingredient.entity.IngredientCategory;
import salute.oneshot.domain.ingredient.entity.QIngredient;

import static salute.oneshot.domain.cocktail.entity.QCocktail.cocktail;

@Repository
@RequiredArgsConstructor
public class CocktailQueryDslRepositoryImpl implements CocktailQueryDslRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<CocktailResponseDto> searchCocktailsByIngredients(List<Ingredient> selectedIngrs,
        Pageable pageable) {


        Set<String> termSet = selectedIngrs.stream()
            .flatMap(ingr -> {
                if (!ingr.getCategory().equals(IngredientCategory.OTHER)) {
                    return Stream.of(ingr.getName(), ingr.getCategory().toString());
                } else {
                    return Stream.of(ingr.getName());
                }
            }).collect(Collectors.toSet());

        List<String> termList = new ArrayList<>(termSet);

        QCocktail cocktail = QCocktail.cocktail;
        QCocktailIngredient cocktailIngr = QCocktailIngredient.cocktailIngredient;
        QIngredient ingredient = QIngredient.ingredient;

        JPQLQuery<?> subquery = JPAExpressions.selectOne()
            .from(cocktailIngr)
            .join(cocktailIngr.ingredient, ingredient)
            .where(
                cocktailIngr.cocktail.eq(cocktail)
                    .and(ingredient.name.notIn(termList))
            );

        List<Cocktail> cocktailList = queryFactory
            .selectFrom(cocktail)
            .where(subquery.notExists())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = queryFactory
            .select(cocktail.id.countDistinct())
            .from(cocktail)
            .where(subquery.notExists())
            .fetchOne();

        List<CocktailResponseDto> response = cocktailList.stream()
            .map(CocktailResponseDto::from)
            .toList();

        return new PageImpl<>(response, pageable, total);

    }

    @Transactional
    public void addViewCntFromRedis(Long cocktailId, Integer viewCount){
       Integer addViewCount = (viewCount == null ? 0 : viewCount);

        queryFactory.update(cocktail)
                .set(cocktail.viewCount, cocktail.viewCount.add(addViewCount))
                .where(cocktail.id.eq(cocktailId))
                .execute();
    }
}
