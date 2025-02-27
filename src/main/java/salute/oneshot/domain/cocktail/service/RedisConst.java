package salute.oneshot.domain.cocktail.service;

import org.springframework.stereotype.Component;

@Component
public class RedisConst {

    public static final String COCKTAIL_SCORE_KEY = "cocktail_score";

    public static final String POPULAR_COCKTAIL_KEY = "popular_cocktail";

    public static final String COCKTAIL_COUNT_KEY_PREFIX = "cocktail_count::";

    public static final String COCKTAIL_SCORE_KEY_PREFIX = "cocktail_score::";
}
