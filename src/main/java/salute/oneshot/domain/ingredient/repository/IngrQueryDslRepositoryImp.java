//package salute.oneshot.domain.ingredient.repository;
//
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//import salute.oneshot.domain.ingredient.dto.response.IngrResponseDto;
//import salute.oneshot.domain.ingredient.entity.Ingredient;
//import static salute.oneshot.domain.ingredient.entity.QIngredient.ingredient;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class IngrQueryDslRepositoryImp implements IngredientQueryDslRepository{
//
//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public List<IngrResponseDto> findIngrByCondition(List<Long> ingrIdList, Pageable pageable) {
//
//
//        List<Ingredient> ingredientList = queryFactory.selectFrom(ingredient)
//                     .where(ingredient.id.in(ingrIdList), cursorId(ingredient.id))
//                     .limit(pageable.getPageSize())
//                     .fetch();
//
//        return ingredientList.stream().map(IngrResponseDto::from).toList();
//
//    }
//
//    private BooleanExpression cursorId(Long lastId){
//        return ingredient.id == null ? null : ingredient.id.gt(lastId);
//    }
//}
