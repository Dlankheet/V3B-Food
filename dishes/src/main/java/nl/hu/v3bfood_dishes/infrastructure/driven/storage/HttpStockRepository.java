package nl.hu.v3bfood_dishes.infrastructure.driven.storage;

import nl.hu.v3bfood_dishes.core.port.storage.StockRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HttpStockRepository implements StockRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpStockRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public List<UUID> findStockByIngredient(String ingredient) {
        URI uri = URI.create(this.rootPath + "/jobs?keyword=" + ingredient);
        StockResult[] results = this.client.getForObject(uri, StockResult[].class);

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> result.id)
                .collect(Collectors.toList());
    }
}
