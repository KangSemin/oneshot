package salute.oneshot.global.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {


    String[] host ;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration
                .builder()
                .connectedTo("host.docker.internal:9200")
                .build();
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {

        // RestClient 생성 (호스트, 포트, 프로토콜 지정)
        RestClient restClient = RestClient.builder(
            new HttpHost(host[0])
        ).build();

        // Transport 생성 (JSON 매퍼 설정)
        ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());

        // ElasticsearchClient 생성 후 반환
        return new ElasticsearchClient(transport);
    }

}
