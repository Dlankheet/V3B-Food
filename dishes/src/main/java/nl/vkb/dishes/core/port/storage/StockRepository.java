package nl.vkb.dishes.core.port.storage;

import nl.vkb.dishes.infrastructure.driven.storage.StockResult;

import java.util.UUID;

public interface StockRepository {
    StockResult findIngredientById(UUID ingredientId);
}
