package salute.oneshot.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salute.oneshot.domain.common.dto.error.ErrorCode;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.dto.service.CreateProductSDto;
import salute.oneshot.domain.product.dto.service.UpdateProductRequestSDto;
import salute.oneshot.domain.product.dto.service.DeleteProductSDto;
import salute.oneshot.domain.product.entity.Product;
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

        verifyAdmin(user);

        Product product = productRepository.save(Product.of(sDto.getName(), sDto.getDescription(), sDto.getPrice(),
                sDto.getStockQuantity(), sDto.getCategory(), sDto.getStatus(), user));

        return ProductResponseDto.from(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(UpdateProductRequestSDto sDto) {

        User user = getUserById(sDto.getUserId());

        verifyAdmin(user);

        Product product = productRepository.findById(sDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));

        product.updateProduct(sDto.getName(),sDto.getDescription(),sDto.getPrice(),
                sDto.getStockQuantity(),sDto.getCategory(),sDto.getStatus());

        return ProductResponseDto.from(product);
    }

    @Transactional
    public void deleteProduct(DeleteProductSDto sDto) {

        User user = getUserById(sDto.getUserId());

        if(user.getUserRole() != UserRole.ADMIN) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }

        Product product = productRepository.findById(sDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND));

        product.deleteProduct();
    }

    private User getUserById(Long userId) {
        return userRepository.findByIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    private void verifyAdmin(User user) {
        if (user.getUserRole() != UserRole.ADMIN) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
