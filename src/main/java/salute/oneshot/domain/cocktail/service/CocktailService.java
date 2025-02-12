package salute.oneshot.domain.cocktail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.cocktail.dto.response.CocktailResponseDto;
import salute.oneshot.domain.cocktail.entity.Cocktail;
import salute.oneshot.domain.cocktail.repository.CocktailQueryDslRepository;
import salute.oneshot.domain.cocktail.repository.CocktailRepository;

@Service
@RequiredArgsConstructor
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public Page<CocktailResponseDto> getAllCocktails(Pageable pageable){

       Page<Cocktail> cocktailPage = cocktailRepository.findAll(pageable);
       Page<CocktailResponseDto> cocktailResponseDtoPage = cocktailPage.map(CocktailResponseDto::from);

       return cocktailResponseDtoPage;
    }




}
