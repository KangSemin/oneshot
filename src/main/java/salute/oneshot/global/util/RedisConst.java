package salute.oneshot.global.util;

import org.springframework.stereotype.Component;

@Component
public class RedisConst {

    public static final String COCKTAIL_SCORE_KEY = "cocktail:score";
    public static final String COCKTAIL_SCORE_SNAPSHOT_KEY = "cocktail:score:snapshot";
    public static final String POPULAR_COCKTAIL_KEY = "popular:cocktail";
    public static final String COCKTAIL_VIEW_ABUSING_KEY = "cocktail:view:abusing:";
    public static final String COCKTAIL_VIEW_COUNT_KEY = "cocktail:view:";
    public static final String COCKTAIL_SCORE_KEY_PREFIX = "cocktail:score:";
}
