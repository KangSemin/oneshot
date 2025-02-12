package salute.oneshot.domain.common.dto.success;

public class ApiResponseMessage {
    // 회원 관련 성공 메시지
    public static final String SIGNUP_SUCCESS = "회원가입이 완료되었습니다.";
    public static final String LOGIN_SUCCESS = "로그인이 완료되었습니다.";
    public static final String LOGOUT_SUCCESS = "로그아웃이 완료되었습니다.";

    // 유저, 셀러 관련 메시지
    public static final String GET_USER_SUCCESS = "사용자 조회가 완료되었습니다.";
    public static final String UPDATE_USER_SUCCESS = "사용자 수정이 완료되었습니다.";
    public static final String DELETE_USER_SUCCESS = "사용자 삭제가 완료되었습니다.";

    // 칵테일 관련 메시지
    public static final String GET_CCKTL_LIST_SUCCESS = "칵테일 목록 조회가 완료되었습니다.";
    public static final String GET_POPULAR_CCKTL_SUCCESS = "인기 칵테일 조회가 완료되었습니다.";
    public static final String GET_CCKTL_SUCCESS = "칵테일 조회가 완료되었습니다.";
    public static final String UPDATE_CCKTL_SUCCESS = "칵테일 정보 수정이 완료되었습니다.";
    public static final String DELETE_CCKTL_SUCCESS = "칵테일 삭제가 완료되었습니다.";

    // 재료 관련 메시지
    public static final String ADD_INGR_SUCCESS = "재료 등록이 완료되었습니다.";
    public static final String GET_INGR_LIST_SUCCESS = "재료 목록 조회가 완료되었습니다.";
    public static final String GET_INGR_SUCCESS = "재료 조회가 완료되었습니다.";
    public static final String UPDATE_INGR_SUCCESS = "재료 정보 수정이 완료되었습니다.";
    public static final String DELETE_INGR_SUCCESS = "재료 삭제가 완료되었습니다.";

    // 레세피 리뷰 관련 메시지
    public static final String ADD_RCP_RVW_SUCCESS = "레세피 리뷰 등록이 완료되었습니다.";
    public static final String GET_RCP_RVW_SUCCESS = "레시피 리뷰 조회가 완료되었습니다.";
    public static final String GET_RCP_RVW_LIST_SUCCESS = "레시피 리뷰 목록 조회가 완료되었습니다.";
    public static final String UPDATE_RCP_RVW_SUCCESS = "레시피 리뷰 수정이 완료되었습니다.";
    public static final String DELETE_RCP_RVW_SUCCESS = "레시피 리뷰 삭제가 완료되었습니다.";

    // 재료 리뷰 관련 메시지
    public static final String ADD_INGR_RVW_SUCCESS = "재료 리뷰 등록이 완료되었습니다.";
    public static final String GET_INGR_RVW_SUCCESS = "재료 리뷰 조회가 완료되었습니다.";
    public static final String GET_INGR_RVW_LIST_SUCCESS = "재료 리뷰 목록 조회가 완료되었습니다.";
    public static final String UPDATE_INGR_RVW_SUCCESS = "재료 리뷰 수정이 완료되었습니다.";
    public static final String DELETE_INGR_RVW_SUCCESS = "재료 리뷰 삭제가 완료되었습니다.";

    //팬트리 관련 메시지
    public static final String ADD_PNTR_INGR_SUCCESS = "팬트리 재료 등록이 완료되었습니다.";
    public static final String GET_PNTR_SUCCESS = "팬트리 조회가 완료되었습니다.";
    public static final String DELETE_PNTR_SUCCESS = "팬트리 비우기가 완료되었습니다.";

    //레시피 관련 메시지
    public static final String ADD_RCP_SUCCESS = "레시피 등록이 완료되었습니다.";
    public static final String GET_O_RCP_LIST_SUCCESS = "공식 레시피 목록 조회가 완료되었습니다.";
    public static final String GET_C_RCP_LIST_SUCCESS = "나만의 레시피 목록 조회가 완료되었습니다.";
    public static final String GET_RCP_SUCCESS = "레시피 조회가 완료되었습니다.";
    public static final String UPDATE_RCP_SUCCESS = "레시피 수정이 완료되었습니다.";
    public static final String DELETE_RCP_SUCCESS = "레시피 삭제가 완료되었습니다.";

    // 즐겨찾기 관련 메시지
    public static final String ADD_FVRT_SUCCESS = "즐겨찾기 등록이 완료되었습니다.";
    public static final String GET_FVRT_LIST_SUCCESS = "즐겨찾기 목록 조회가 완료되었습니다.";
    public static final String GET_FVRT_COUNT_SUCCESS = "레시피의 즐겨찾기 수 조회가 완료되었습니다.";
    public static final String DELETE_FVRT_SUCCESS = "즐겨찾기 해제가 완료되었습니다.";

    // 장바구니 관련 메시지
    public static final String ADD_CART_ITEM_SUCCESS = "장바구니 추가가 완료되었습니다.";
    public static final String GET_CART_SUCCESS = "장바구니 조회가 완료되었습니다.";
    public static final String EMPTY_CART_SUCCESS = "즐겨찾기 해제가 완료되었습니다.";
}
