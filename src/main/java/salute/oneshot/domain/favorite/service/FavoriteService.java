package salute.oneshot.domain.favorite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import salute.oneshot.domain.favorite.repository.FavoriteRepository;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private FavoriteRepository favoriteRepository;
}
