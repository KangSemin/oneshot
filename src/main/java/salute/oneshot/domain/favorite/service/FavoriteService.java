package salute.oneshot.domain.favorite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.favorite.dto.response.FavoritePageResponseDto;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.response.GetFavoriteStatusDto;
import salute.oneshot.domain.favorite.dto.service.DeleteFavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.FavoriteSDto;
import salute.oneshot.domain.favorite.dto.service.GetFavoritesSDto;
import salute.oneshot.domain.favorite.entity.Favorite;
import salute.oneshot.domain.favorite.repository.FavoriteRepository;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.ForbiddenException;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.util.RedisConst;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;

    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public FavoriteResponseDto createFavorite(FavoriteSDto serviceDto) {
        Long userId = serviceDto.getUserId();
        Long cocktailId = serviceDto.getCocktailId();

        if (favoriteRepository.existsByRecipeIdAndUserId(cocktailId, userId)) {
            throw new ConflictException(ErrorCode.DUPLICATE_FAVORITE);
        }

        Cocktail cocktail = cocktailRepository.findById(cocktailId)
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.COCKTAIL_NOT_FOUND));

        Favorite favorite = Favorite.from(userId, cocktail);
        favoriteRepository.save(favorite);

        incrementFavoriteScore(cocktailId);

        return FavoriteResponseDto.from(cocktail, favorite);
    }

    @Transactional(readOnly = true)
    public GetFavoriteStatusDto checkFavorite(FavoriteSDto serviceDto) {
        boolean status = favoriteRepository.existsByRecipeIdAndUserId(
                serviceDto.getCocktailId(), serviceDto.getUserId());

        return GetFavoriteStatusDto.of(status);
    }

    @Transactional(readOnly = true)
    public FavoritePageResponseDto getFavorites(GetFavoritesSDto serviceDto) {
        Page<Favorite> favorites = favoriteRepository.findAllByUserId(
                serviceDto.getId(),
                serviceDto.getPageable());

        Page<FavoriteResponseDto> favoritePage = favorites.map(favorite ->
                FavoriteResponseDto.from(favorite.getCocktail(), favorite));

        return FavoritePageResponseDto.from(favoritePage);
    }

    @Transactional
    public FavoriteResponseDto deleteFavorite(DeleteFavoriteSDto serviceDto) {
        Favorite favorite = favoriteRepository
                .findByIdAndUserId(serviceDto.getFavoriteId(), serviceDto.getUserId())
                .orElseThrow(() ->
                        new ForbiddenException(ErrorCode.FORBIDDEN_ACCESS));

        favoriteRepository.delete(favorite);

        return FavoriteResponseDto.from(favorite.getCocktail(), favorite);
    }

    public void incrementFavoriteScore(Long cocktailId) {
        String cocktailCountKey = RedisConst.COCKTAIL_COUNT_KEY_PREFIX + cocktailId;
        String cocktailScoreKey = RedisConst.COCKTAIL_SCORE_KEY_PREFIX + cocktailId;

        redisTemplate.opsForHash().increment(cocktailCountKey, "favoriteCount",1);
        redisTemplate.opsForZSet().incrementScore(RedisConst.COCKTAIL_SCORE_KEY,cocktailScoreKey, 2);
    }
}
