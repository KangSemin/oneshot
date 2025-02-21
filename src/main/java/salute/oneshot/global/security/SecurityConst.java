package salute.oneshot.global.security;

public class SecurityConst {
    // JWT 관련 상수
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final long ACCESS_TTL = 60 * 60 * 1000L; // 1hour

    // 에러 메시지
    public static final String INVALID_TOKEN = "유요하지 않은 토큰입니다.";

    // 로그 메시지
    public static final String AUTH_ERROR_LOG = "Security Authentication error: {}, Message: {}";

    // 응답 타입
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
}
