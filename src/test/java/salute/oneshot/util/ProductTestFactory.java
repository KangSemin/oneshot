package salute.oneshot.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;
import salute.oneshot.domain.product.dto.request.CreateProductRequestDto;
import salute.oneshot.domain.product.dto.request.UpdateProductRequestDto;
import salute.oneshot.domain.product.dto.response.ProductResponseDto;
import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProductTestFactory {

    public static final Long PRODUCT_ID = 1L;
    public static final String NAME = "보드카";
    public static final String DESCRIPTION = "독한 술입니다.";
    public static final int PRICE = 120000;
    public static final int STOCK_QUANTITY = 50;
    public static final ProductCategory CATEGORY= ProductCategory.ALCOHOL;
    public static final ProductStatus STATUS = ProductStatus.SALE;

    public static Product createProduct() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<Product> constructor = Product.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Product product = constructor.newInstance();

        ReflectionTestUtils.setField(product, "id", PRODUCT_ID);
        ReflectionTestUtils.setField(product, "name", NAME);
        ReflectionTestUtils.setField(product, "description", DESCRIPTION);
        ReflectionTestUtils.setField(product, "price", PRICE);
        ReflectionTestUtils.setField(product, "stockQuantity", STOCK_QUANTITY);
        ReflectionTestUtils.setField(product, "category", CATEGORY);
        ReflectionTestUtils.setField(product, "status", STATUS);
        ReflectionTestUtils.setField(product, "user", UserTestFactory.createUser());

        return product;
    }

    public static CreateProductRequestDto createCreateProductRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<CreateProductRequestDto> constructor =
                CreateProductRequestDto.class.getDeclaredConstructor(String.class, String.class, ProductCategory.class, int.class, int.class, ProductStatus.class);
        constructor.setAccessible(true);

        return constructor.newInstance(NAME, DESCRIPTION, CATEGORY, PRICE, STOCK_QUANTITY, STATUS);
    }

    public static ProductResponseDto createProductResponseDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<ProductResponseDto> constructor =
                ProductResponseDto.class.getDeclaredConstructor(Long.class, String.class, String.class, int.class, int.class);
        constructor.setAccessible(true);

        return constructor.newInstance(PRODUCT_ID, NAME, DESCRIPTION, PRICE, STOCK_QUANTITY);
    }

    public static UpdateProductRequestDto createUpdateProductRequestDto() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<UpdateProductRequestDto> constructor =
                UpdateProductRequestDto.class.getDeclaredConstructor(String.class, String.class, ProductCategory.class, int.class, int.class, ProductStatus.class);
        constructor.setAccessible(true);

        return constructor.newInstance(NAME, DESCRIPTION, CATEGORY, PRICE, STOCK_QUANTITY, STATUS);
    }

    public static Page<ProductResponseDto> createProductResponseDtoPage() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ProductResponseDto> productList = List.of(createProductResponseDto());
        return new PageImpl<>(productList);
    }
}
