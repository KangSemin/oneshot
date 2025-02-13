package salute.oneshot.domain.favorite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.favorite.dto.response.FavoriteResponseDto;
import salute.oneshot.domain.favorite.dto.service.CreateFavoriteSDto;
import salute.oneshot.domain.favorite.entity.Favorite;
import salute.oneshot.domain.favorite.repository.FavoriteRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.ConflictException;
import salute.oneshot.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final CocktailRepository cocktailRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    @Transactional
    public FavoriteResponseDto createFavorite(CreateFavoriteSDto serviceDto) {
        if (favoriteRepository.existsByRecipeIdAndUserId(
                serviceDto.getRecipeId(), serviceDto.getUserId())) {
            throw new ConflictException(ErrorCode.DUPLICATE_FAVORITE);
        }

        User proxyUser = userRepository.getReferenceById(serviceDto.getUserId());
        Cocktail cocktail = cocktailRepository.findById(serviceDto.getRecipeId())
                .orElseThrow(() ->
                        new NotFoundException(ErrorCode.RECIPE_NOT_FOUND));

        Favorite favorite = Favorite.from(proxyUser, cocktail);
        favoriteRepository.save(favorite);

        return FavoriteResponseDto.from(cocktail, favorite);
    }
}
