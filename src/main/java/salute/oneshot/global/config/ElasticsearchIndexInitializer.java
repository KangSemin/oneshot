//package salute.oneshot.global.config;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.IndexOperations;
//import org.springframework.stereotype.Component;
//import salute.oneshot.domain.cocktail.entity.CocktailDocument;
//
//@Component
//public class ElasticsearchIndexInitializer {
//
//    private final ElasticsearchOperations operations;
//
//    public ElasticsearchIndexInitializer(ElasticsearchOperations operations) {
//        this.operations = operations;
//    }
//
//    @PostConstruct
//    public void initIndices() {
//        IndexOperations indexOps = operations.indexOps(CocktailDocument.class);
//        if (!indexOps.exists()) {
//            indexOps.create();
//            indexOps.putMapping(indexOps.createMapping());
//        }
//    }
//}
