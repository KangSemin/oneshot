package salute.oneshot.global.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;

@Configuration
@EnableElasticsearchRepositories
@RequiredArgsConstructor
//public class ElasticSearchConfig extends ElasticsearchConfiguration {
public class ElasticSearchConfig {

    @Value("${opensearch.host}")
    private String host;
    @Value("${opensearch.port}")
    private int port;
    @Value("${opensearch.username}")
    private String username;
    @Value("${opensearch.password}")
    private String password;
    private final ObjectMapper objectMapper;


    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // Basic 인증 설정
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        Header[] defaultHeaders = new Header[] {
                new BasicHeader("Content-Type", "application/json")
        };

        // RestClientBuilder에 인증 정보 적용
        RestClientBuilder builder = RestClient
                .builder(new HttpHost(host, port, "https"))
                .setDefaultHeaders(defaultHeaders)
                .setHttpClientConfigCallback(httpClientBuilder ->
                        httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider)
                                .addInterceptorLast(new HttpResponseInterceptor() {
                                    @Override
                                    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                                        response.addHeader("X-Elastic-Product", "Elasticsearch");
                                    }
                                })
                );

        RestClient restClient = builder.build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));

        return new ElasticsearchClient(transport);
    }

//    @Override
//    public ClientConfiguration clientConfiguration() {
//        HttpHeaders defaultHeaders = new HttpHeaders();
//        defaultHeaders.add("Content-Type", "application/json");
//
//        return ClientConfiguration
//                .builder()
//                .connectedTo(host + ":" + port)
////                .connectedTo(host)
//                .usingSsl()
//                .withBasicAuth(username, password)
//                .withDefaultHeaders(defaultHeaders)
//                .build();
//    }

}
