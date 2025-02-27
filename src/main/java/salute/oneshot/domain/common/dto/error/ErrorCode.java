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
    DUPLICATE_ROLE(HttpStatus.CONFLICT, "동일한 권한입니다."),

    // 칵테일 관련 익셉션
    COCKTAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "칵테일이 존재하지 않습니다."),

    // 즐겨찾기 관련 익셉션
    FAVORITE_NOT_FOUND(HttpStatus.NOT_FOUND, "즐겨찾기가 존재하지 않습니다."),
    DUPLICATE_FAVORITE(HttpStatus.CONFLICT, "이미 즐겨찾기한 레시피입니다." ),

    // 재료 관련 익셉션
    INGREDIENT_NOT_FOUND(HttpStatus.NOT_FOUND, "재료가 존재하지 않습니다."),
    DUPLICATE_INGREDIENT(HttpStatus.CONFLICT, "이미 등록된 재료입니다." ),

    // 리뷰 관련 익셉션
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰가 존재하지 않습니다."),
    REVIEW_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN,"본인이 작성한 리뷰만 삭제할 수 있습니다."),
    REVIEW_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN,"본인이 작성한 리뷰만 수정할 수 있습니다."),

    // 팬트리 관련 익셉션
    PANTRY_NOT_FOUND(HttpStatus.NOT_FOUND, "팬트리가 존재하지 않습니다."),

    // 상품 관련 익셉션
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다."),

    // 장바구니 관련 익셉션
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "장바구니가 존재하지 않습니다."),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 항목이 존재하지 않습니다."),
    FULL_CART(HttpStatus.NOT_FOUND, "장바구니에 항목을 더 추가할 수 없습니다."),
    INVALID_CART_ITEM_QUANTITY(HttpStatus.NOT_FOUND, "수량이 1보다 작을 수 없습니다."),
    CART_ITEM_FORBIDDEN(HttpStatus.FORBIDDEN, "본인의 장바구니만 변경할 수 있습니다."),

    // 주문 관련 익셉션
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "주문이 존재하지 않습니다."),
    ORDER_GET_FORBIDDEN(HttpStatus.FORBIDDEN,"다른 사용자의 주문 내역은 조회할 수 없습니다."),
    INVALID_ORDER_STATUS(HttpStatus.BAD_REQUEST, "현재 상태에서는 주문 상태를 변경할 수 없습니다."),
    CANNOT_CANCEL_SHIPPED_ORDER(HttpStatus.BAD_REQUEST, "배송 완료된 주문은 취소할 수 없습니다."),
    ORDER_CANCEL_FORBIDDEN(HttpStatus.FORBIDDEN,"이 주문을 취소할 권한이 없습니다."),
    ALREADY_CANCELLED_ORDER(HttpStatus.BAD_REQUEST, "이미 취소처리 된 주문입니다."),

    // 결제 관련 익셉션
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 결제 내역이 존재하지 않습니다."),
    PAYMENT_FORBIDDEN(HttpStatus.NOT_FOUND, "본인의 결제 내역만 조회할 수 있습니다."),
    PAYMENT_ALREADY_CONFIRMED(HttpStatus.CONFLICT, "이미 만료된 결제입니다."),
    ORDER_ALREADY_PAID(HttpStatus.CONFLICT, "이미 결제가 된 주문입니다."),
    WRONG_PAYMENT_AMOUNT(HttpStatus.CONFLICT, "결제 금액과 주문 금액이 다릅니다."),

    // 레시피 관련 익셉션
    RECIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "레시피가 존재하지 않습니다."),

    // 주소 관련 익셉션
    ADR_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 주소가 존재하지 않습니다."),
    DUPLICATE_ADR_DEFAULT(HttpStatus.CREATED, "이미 기본값으로 설정된 주소입니다."),
    INVALID_ADDRESS_ACCESS(HttpStatus.FORBIDDEN, "해당 주소에 대한 접근 권한이 없습니다."),
    DEFAULT_ADDRESS_REQUIRED(HttpStatus.BAD_REQUEST, "기본 주소는 필수입니다"),

    // 배송 관련 익셉션
    INVALID_SHIPPING_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 Code 입니다."),
    DUPLICATE_SHIPPING(HttpStatus.CONFLICT, "이미 배송정보가 존재하는 주문입니다."),
    INVALID_ORDER_STATE(HttpStatus.BAD_REQUEST,"배송 대기 중인 주문만 등록할 수 있습니다"),
    SHIPPING_NOT_FOUND(HttpStatus.NOT_FOUND, "배송 정보가 존재하지 않습니다."),
    SAME_STATUS_UPDATE(HttpStatus.BAD_REQUEST, "이미 해당 배송 상태입니다"),
    INVALID_STATUS_CHANGE(HttpStatus.BAD_REQUEST, "유효하지 않은 배송 상태 변경입니다"),

    // 배너 관련 익셉션
    BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "배너가 존재하지 않습니다."),

    // 쿠폰 관련 익셉션
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "쿠폰이 존재하지 않습니다"),
    INVALID_COUPON_STATUS(HttpStatus.BAD_REQUEST, "유효하지 않은 쿠폰 상태입니다."),
    INVALID_USER_COUPON_STATUS(HttpStatus.BAD_REQUEST, "유효하지 않은 유저쿠폰 상태입니다."),
    VALID_DAYS_REQUIRED(HttpStatus.BAD_REQUEST, "유효 기간을 입력해주세요."),
    VALID_DAYS_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "만료 기간이 없는 쿠폰입니다."),
    EXPIRED_COUPON(HttpStatus.BAD_REQUEST, "만료된 쿠폰입니다."),

    // 이벤트 관련 익셉션
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "이벤트가 존재하지 않습니다."),
    INVALID_EVENT_STATUS(HttpStatus.BAD_REQUEST, "유효하지 않은 이벤트 상태입니다."),
    EXPIRED_EVENT(HttpStatus.BAD_REQUEST, "이벤트 종료일이 현재 시간보다 이전입니다."),
    INVALID_EVENT_PERIOD(HttpStatus.BAD_REQUEST, "이벤트 시작일이 종료일보다 이후입니다."),
    CPN_EXPIRES_BEFORE_EVENT(HttpStatus.BAD_REQUEST, "쿠폰이 이벤트 종료일보다 먼저 만료됩니다."),
    INVALID_DATETIME(HttpStatus.BAD_REQUEST, "유효하지 않은 날짜 또는 시간 형식입니다."),
    INVALID_EVENT_TYPE(HttpStatus.BAD_REQUEST,"유효하지 않은 이벤트 타입 입니다."),
    INVALID_JSON_DATA(HttpStatus.BAD_REQUEST, "JSON 데이터 검증 중 오류가 발생했습니다."),
    MISSING_COUPON(HttpStatus.BAD_REQUEST, "쿠폰 정보가 누락되었습니다."),
    INVALID_LIMIT_COUNT(HttpStatus.BAD_REQUEST, "투표 제한 횟수가 유효하지 않습니다."),
    INVALID_COUPON_COUNT(HttpStatus.BAD_REQUEST, "쿠폰 갯수가 유효하지 않습니다."),

    // VOTE 이벤트 관련 익셉션
    INVALID_CANDIDATE(HttpStatus.BAD_REQUEST, "후보자 정보가 유효하지 않습니다."),
    INVALID_CANDIDATE_COUNT(HttpStatus.BAD_REQUEST, "후보자 수가 유효하지 않습니다."),

    // 소셜로그인 관련 익셉션
    INVALID_PROVIDER_NAME(HttpStatus.BAD_REQUEST, "유효하지 않은 소셜로그인 제공자 입니다"),

    // 인가 관련 익셉션
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),

    // 인증 관련 익셉션
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "잘못된 아이디 또는 비밀번호입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),;

    private final HttpStatus httpStatus;
    private final String message;
}
