package salute.oneshot.global.security.model;

public class SecurityConst {
    // JWT 관련 상수
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final long ACCESS_TTL = 60 * 60 * 1000L; // 1hour

    // 에러 메시지
    public static final String INVALID_TOKEN = "유효하지 않은 토큰입니다.";

    // 로그 메시지
    public static final String AUTH_ERROR_LOG = "Security Authentication error: {}, Message: {}";

    // 응답 타입
    public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    // CSP 관련
    public static class CSP {
        // 기본 정책
        public static final String DEFAULT_SRC = "default-src 'self';";

        // 스크립트 소스
        public static final String SCRIPT_SRC_SELF = "'self'";
        public static final String SCRIPT_SRC_DAUM = "https://t1.daumcdn.net https://spi.maps.daum.net";
        public static final String SCRIPT_SRC_TOSS = "https://js.tosspayments.com https://log.tosspayments.com";
        public static final String SCRIPT_SRC_CDNJS = "https://cdnjs.cloudflare.com";
        public static final String SCRIPT_SRC_OAUTH2 = "https://accounts.google.com https://static.nid.naver.com";
        public static final String SCRIPT_SRC_BOOTSTRAP = "https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com";
        // 프레임 소스
        public static final String FRAME_SRC_SELF = "'self'";
        public static final String FRAME_SRC_DAUM = "http://postcode.map.daum.net";
        public static final String FRAME_SRC_TOSS = "https://payment-gateway-sandbox.tosspayments.com";
        public static final String FRAME_SRC_OAUTH2 = "https://accounts.google.com https://nid.naver.com";

        // 스타일 소스
        public static final String STYLE_SRC_SELF = "'self'";
        public static final String STYLE_SRC_UNSAFE_INLINE = "'unsafe-inline'";
        public static final String STYLE_SRC_GOOGLE_FONTS = "https://fonts.googleapis.com";
        public static final String STYLE_SRC_OAUTH2 = "https://accounts.google.com https://nid.naver.com";
        public static final String STYLE_SRC_BOOTSTRAP = "https://cdn.jsdelivr.net https://stackpath.bootstrapcdn.com";

        // 폰트 소스
        public static final String FONT_SRC_SELF = "'self'";
        public static final String FONT_SRC_GOOGLE_FONTS = "https://fonts.gstatic.com";

//        // 이미지 소스
//        public static final String IMG_SRC_SELF = "'self'";
//        public static final String IMG_SRC_OAUTH2 = "https://www.gstatic.com https://ssl.gstatic.com https://static.nid.naver.com";

        // 폼 액션
        public static final String FORM_ACTION_SELF = "'self'";
        public static final String FORM_ACTION_OAUTH2 = "https://accounts.google.com https://nid.naver.com";

        // 연결 소스
        public static final String CONNECT_SRC_SELF = "'self'";
        public static final String CONNECT_SRC_TOSS = "https://log.tosspayments.com https://event.tosspayments.com https://apigw-sandbox.tosspayments.com";
        public static final String CONNECT_SRC_OAUTH2 = "https://*.googleapis.com https://openapi.naver.com";
        public static final String CONNECT_SRC_WS = "ws";

        // CSP 정책 생성 메서드
        public static String buildScriptSrcPolicy(String nonce) {
            return String.format("script-src %s %s %s %s %s %s 'nonce-%s';",
                    SCRIPT_SRC_SELF,
                    SCRIPT_SRC_DAUM,
                    SCRIPT_SRC_TOSS,
                    SCRIPT_SRC_CDNJS,
                    SCRIPT_SRC_OAUTH2,
                    SCRIPT_SRC_BOOTSTRAP,
                    nonce);
        }

        public static String buildFrameSrcPolicy() {
            return String.format("frame-src %s %s %s %s;",
                    FRAME_SRC_SELF,
                    FRAME_SRC_DAUM,
                    FRAME_SRC_TOSS,
                    FRAME_SRC_OAUTH2);
        }

        public static String buildStyleSrcPolicy() {
            return String.format("style-src %s %s %s %s %s;",
                    STYLE_SRC_SELF,
                    STYLE_SRC_UNSAFE_INLINE,
                    STYLE_SRC_GOOGLE_FONTS,
                    STYLE_SRC_OAUTH2,
                    STYLE_SRC_BOOTSTRAP);
        }

        public static String buildFontSrcPolicy() {
            return String.format("font-src %s %s;",
                    FONT_SRC_SELF,
                    FONT_SRC_GOOGLE_FONTS);
        }

        public static String buildImgSrcPolicy() {
            return "img-src * data:;";
        }

        public static String buildFormActionPolicy() {
            return String.format("form-action %s %s;",
                    FORM_ACTION_SELF,
                    FORM_ACTION_OAUTH2);
        }

        public static String buildConnectSrcPolicy() {
            return String.format("connect-src %s %s %s %s;",
                    CONNECT_SRC_SELF,
                    CONNECT_SRC_TOSS,
                    CONNECT_SRC_OAUTH2,
                    CONNECT_SRC_WS);
        }

        // 전체 CSP 정책 생성
        public static String buildFullPolicy(String nonce) {
            return DEFAULT_SRC + " " +
                    buildScriptSrcPolicy(nonce) + " " +
                    buildFrameSrcPolicy() + " " +
                    buildStyleSrcPolicy() + " " +
                    buildFontSrcPolicy() + " " +
                    buildImgSrcPolicy() + " " +
                    buildFormActionPolicy() + " " +
                    buildConnectSrcPolicy();
        }
    }
}
