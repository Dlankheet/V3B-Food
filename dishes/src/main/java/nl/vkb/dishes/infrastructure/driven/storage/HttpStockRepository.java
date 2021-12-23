package nl.vkb.dishes.infrastructure.driven.storage;

import nl.vkb.dishes.core.port.storage.StockRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

public class HttpStockRepository implements StockRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpStockRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public StockResult findIngredientById(UUID ingredientId) {
        URI uri = URI.create(this.rootPath + "/ingredient/" + ingredientId);
        StockResult sr = this.client.getForObject(uri, StockResult.class);
        UUID id = sr.getId();
        System.out.println(id);
        return sr;
    }
}
