package nl.vkb.dishes.infrastructure.driven.storage;

import java.util.UUID;

public class StockResult {
    public UUID id;
    public String name;
    public int stock;

    public StockResult(UUID id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }
}
