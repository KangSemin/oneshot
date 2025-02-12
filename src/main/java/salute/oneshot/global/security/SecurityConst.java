package salute.oneshot.global.security;

public class SecurityConst {
    // JWT 관련 상수
    public static final String BEARER_PREFIX = "Bearer ";
    public static final long TOKEN_TIME = 60 * 60 * 1000L; // 1hour
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "Bearer";

    // 에러 메시지
    public static final String TOKEN_VALIDATION_FAIL = "JWT 보안 검증에 실패했습니다.";
    public static final String MALFORMED_TOKEN = "잘못된 JWT 형식입니다.";
    public static final String EXPIRED_TOKEN = "만료된 JWT 토큰입니다.";
    public static final String UNSUPPORTED_TOKEN = "지원되지 않는 JWT 토큰입니다.";
    public static final String UNKNOWN_TOKEN_ERROR = "JWT 토큰 처리 중 알 수 없는 오류가 발생했습니다.";

    // 로그 메시지
    public static final String AUTH_ERROR_LOG = "Security Authentication error: {}, Message: {}";

    // 응답 타입
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
}
