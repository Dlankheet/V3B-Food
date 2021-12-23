package nl.vkb.dishes.core.port.storage;

import nl.vkb.dishes.infrastructure.driven.storage.StockResult;

import java.util.List;
import java.util.UUID;

public interface StockRepository {
    StockResult findIngredientById(UUID ingredientId);
    List<StockResult> findIngredientByIds(List<UUID> ids);
}
