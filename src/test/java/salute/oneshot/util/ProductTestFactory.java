package salute.oneshot.util;

import salute.oneshot.domain.product.entity.Product;
import salute.oneshot.domain.product.entity.ProductCategory;
import salute.oneshot.domain.product.entity.ProductStatus;

public class ProductTestFactory {

    public static final Long PRODUCT_ID = 1L;
    public static final String NAME = "보드카";
    public static final String DESCRIPTION = "독한 술입니다.";
    public static final int PRICE = 120000;
    public static final int STOCK_QUANTITY = 50;

    public static Product createProduct() {
        return Product.of(NAME, DESCRIPTION, PRICE, STOCK_QUANTITY, ProductCategory.ALCOHOL, ProductStatus.SALE, UserTestFactory.createUser());
    }
}
