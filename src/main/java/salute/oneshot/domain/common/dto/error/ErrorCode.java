package salute.oneshot.domain.common.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 유저 관련 익셉션
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, "유효하지 않은 UerRole 입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다."),
    DUPLICATE_USER_DELETE(HttpStatus.CONFLICT, "이미 탈퇴한 사용자입니다."),

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
  
    // 레시피 관련 익셉션
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "레시피가 존재하지 않습니다."),

    // 인가 관련 익셉션
    FORBIDDEN_ACCESS(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다." ),

    // 인증 관련 익셉션
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "잘못된 아이디 또는 비밀번호입니다."),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, "JWT 토큰이 없습니다."),
    TOKEN_VALIDATION_FAIL(HttpStatus.UNAUTHORIZED, "JWT 보안 검증에 실패했습니다."),
    MALFORMED_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 JWT 형식입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "지원되지 않는 JWT 토큰입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
