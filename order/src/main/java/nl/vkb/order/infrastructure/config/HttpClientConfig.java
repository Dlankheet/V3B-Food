package nl.vkb.order.infrastructure.config;

import nl.vkb.order.infrastructure.driven.storage.HttpDishRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.dish}")
    private String rootPath;

    @Bean
    public HttpDishRepository httpDishRepository() {
        return new HttpDishRepository(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
