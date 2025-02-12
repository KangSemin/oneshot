package salute.oneshot.domain.common.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 유저 관련 익셉션
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),

    // 칵테일 관련 익셉션
    COCKTAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "칵테일이 존재하지 않습니다."),

    // 즐겨찾기 관련 익셉션
    FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND, "즐겨찾기가 존재하지 않습니다."),

    // 재료 관련 익셉션
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "재료가 존재하지 않습니다."),

    // 리뷰 관련 익셉션
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰가 존재하지 않습니다."),

    // 팬트리 관련 익셉션
    PANTRY_NOT_FOUND(HttpStatus.NOT_FOUND, "팬트리가 존재하지 않습니다."),

    // 상품 관련 익셉션
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."),

    // 장바구니 관련 익셉션
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니가 존재하지 않습니다."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 항목이 존재하지 않습니다."),
    FULL_CART(HttpStatus.NOT_FOUND, "장바구니에 항목을 더 추가할 수 없습니다."),
    INVALID_CART_ITEM_QUANTITY(HttpStatus.NOT_FOUND, "수량이 1보다 작을 수 없습니다."),
    CART_ITEM_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "본인의 장바구니만 변경할 수 있습니다."),

  
    // 레시피 관련 익셉션
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "레시피가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
