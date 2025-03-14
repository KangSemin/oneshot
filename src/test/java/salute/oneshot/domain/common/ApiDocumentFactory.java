package salute.oneshot.domain.common;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

public class ApiDocumentFactory {

    // 태그
    public static final String ADDRESS_TAG = "Address API";
    public static final String AUTH_TAG = "Auth API";
    public static final String BANNER_TAG = "Banner API";
    public static final String CART_TAG = "Cart API";
    public static final String CHAT_TAG = "Chat API";
    public static final String COCKTAIL_TAG = "Cocktail API";
    public static final String COUPON_TAG = "Coupon API";
    public static final String DELIVERY_TAG = "Delivery API";
    public static final String EVENT_TAG = "Event API";
    public static final String FAVORITE_TAG = "Favorite API";
    public static final String INGREDIENT_TAG = "Ingredient API";
    public static final String INGREDIENT_REVIEW_TAG = "IngredientReview API";
    public static final String ORDER_TAG = "Order API";
    public static final String PAYMENT_TAG = "Payment API";
    public static final String PRODUCT_TAG = "Product API";
    public static final String RECIPE_REVIEW_TAG = "RecipeReview API";
    public static final String USER_TAG = "User API";

    // 파라미터
    public static final ParameterDescriptorWithType LAST_ID_PARAM =
            new ParameterDescriptorWithType("lastAddressId").description("마지막으로 조회한 ID").type(SimpleType.STRING).optional();

    public static final ParameterDescriptorWithType SIZE_PARAM =
            new ParameterDescriptorWithType("size").description("페이지 크기").type(SimpleType.STRING).defaultValue("10");

    public static final ParameterDescriptorWithType PAGE_PARAM =
            new ParameterDescriptorWithType("page").description("페이지 번호").type(SimpleType.STRING).defaultValue("1");

    public static final ParameterDescriptorWithType START_DATE_PARAM =
            new ParameterDescriptorWithType("startDate").description("시작일").type(SimpleType.STRING).defaultValue("2025-03-10").optional();

    public static final ParameterDescriptorWithType END_DATE_PARAM =
            new ParameterDescriptorWithType("endDate").description("종료일").type(SimpleType.STRING).defaultValue("2025-03-11").optional();

    public static final ParameterDescriptorWithType EVENT_START_DATE_PARAM =
            new ParameterDescriptorWithType("eventStartDate").description("이벤트 시작일").type(SimpleType.STRING).defaultValue("2025-03-10").optional();

    public static final ParameterDescriptorWithType EVENT_END_DATE_PARAM =
            new ParameterDescriptorWithType("eventEndDate").description("이벤트 종료일").type(SimpleType.STRING).defaultValue("2025-03-11").optional();

    public static final ParameterDescriptorWithType COUPON_STATUS_PARAM =
            new ParameterDescriptorWithType("status").description("쿠폰 상태").type(SimpleType.STRING).defaultValue("issued").optional();

    public static final ParameterDescriptorWithType EVENT_STATUS_PARAM =
            new ParameterDescriptorWithType("status").description("이벤트 상태").type(SimpleType.STRING).defaultValue("ongoing").optional();

    public static final ParameterDescriptorWithType EVENT_TYPE_PARAM =
            new ParameterDescriptorWithType("type").description("이벤트 타입").type(SimpleType.STRING).defaultValue("fcfs").optional();

    public static RestDocumentationResultHandler listDoc(
            String identifier,
            String tagName,
            String summary,
            String description,
            ParameterDescriptorWithType... params
    ) {
        return document(identifier,
                resource(ResourceSnippetParameters.builder()
                        .tag(tagName)
                        .summary(summary)
                        .description(description)
                        .queryParameters(params)
                        .build()));
    }

    public static RestDocumentationResultHandler listDoc(
            String identifier,
            String tagName,
            String summary,
            String description
    ) {
        return document(identifier,
                resource(ResourceSnippetParameters.builder()
                        .tag(tagName)
                        .summary(summary)
                        .description(description)
                        .build()));
    }
}
