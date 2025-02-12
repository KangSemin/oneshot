package salute.oneshot.domain.cocktail.dto.response;

import salute.oneshot.domain.cocktail.entity.Cocktail;

import java.time.LocalDateTime;

public class CocktailResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private CocktailResponseDto(Long id, String name, String description,
                                LocalDateTime createdAt, LocalDateTime modifiedAt
    ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }

    public static CocktailResponseDto from(Cocktail cocktail){
        return new CocktailResponseDto(cocktail.getId(), cocktail.getName(),
                cocktail.getDescription(), cocktail.getCreatedAt(), cocktail.getUpdatedAt());
    }
}
