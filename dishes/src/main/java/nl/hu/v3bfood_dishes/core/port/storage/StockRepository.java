package nl.hu.v3bfood_dishes.core.port.storage;

import nl.hu.v3bfood_dishes.infrastructure.driven.storage.StockResult;

import java.util.List;
import java.util.UUID;

public interface StockRepository {
    StockResult findIngredientById(UUID ingredientId);
}
