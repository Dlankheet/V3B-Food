package nl.vkb.dishes.infrastructure.configuration;

import nl.vkb.dishes.infrastructure.driven.storage.HttpStockRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.stock}")
    private String rootPath;

    @Bean
    public HttpStockRepository httpJobRepository() {
        return new HttpStockRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
