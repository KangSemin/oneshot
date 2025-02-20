package salute.oneshot.global.security;

public class SecurityConst {
    // JWT 관련 상수
    public static final String BEARER_PREFIX = "Bearer ";;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L; // 1hour
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L; // 7days

    // 에러 메시지
    public static final String INVALID_TOKEN = "유요하지 않은 토큰입니다.";

    // 로그 메시지
    public static final String AUTH_ERROR_LOG = "Security Authentication error: {}, Message: {}";

    // 응답 타입
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
}
