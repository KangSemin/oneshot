package salute.oneshot.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.dto.service.CreateProductSDto;
import salute.oneshot.domain.product.dto.service.GetAllProductSDto;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;
import salute.oneshot.domain.product.repository.ProductRepository;
import salute.oneshot.domain.user.entity.User;
import salute.oneshot.domain.user.entity.UserRole;
import salute.oneshot.domain.user.repository.UserRepository;
import salute.oneshot.global.exception.NotFoundException;
import salute.oneshot.global.exception.UnauthorizedException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public ProductResponseDto createProduct(CreateProductSDto sDto) {

        User user = getUserById(sDto.getUserId());

        if (user.getUserRole() != UserRole.ADMIN) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }

        Product product = productRepository.save(Product.of(sDto.getName(), sDto.getDescription(), sDto.getPrice(),
                sDto.getStockQuantity(), sDto.getCategory(), sDto.getStatus(), user));

        return ProductResponseDto.from(product);
    }

    public Page<ProductResponseDto> getAllProduct(GetAllProductSDto sDto) {

        ProductCategory category = null;

        if(sDto.getCategory() != null) {
            category = ProductCategory.valueOf(sDto.getCategory());
        }

        Page<Product> productPage = productRepository
                .findByCategoryANDStatusNot(ProductStatus.DELETED, category, sDto.getPageable());

        Page<ProductResponseDto> responseDtos = productPage.map(ProductResponseDto::from);

        return responseDtos;
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
