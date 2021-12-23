package nl.vkb.dishes.infrastructure.driven.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class StockResult {
    private final UUID id;
    private final String name;
    private final int stock;

    public StockResult(UUID id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}
