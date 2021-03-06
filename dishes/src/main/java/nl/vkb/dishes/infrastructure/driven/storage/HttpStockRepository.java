package nl.vkb.dishes.infrastructure.driven.storage;

import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.utils.UuidUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
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
        return this.client.getForObject(uri, StockResult.class);
    }

    @Override
    public List<StockResult> findIngredientByIds(List<UUID> ingredientIds) {
        URI uri = URI.create(this.rootPath + "/ingredient/all?filer=" + UuidUtils.parseUUIDListToString(ingredientIds));
        StockResult[] stockResultList = this.client.getForObject(uri, StockResult[].class);
        return List.of(stockResultList);
    }
}
