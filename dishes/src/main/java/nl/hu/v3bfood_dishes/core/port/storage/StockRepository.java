package nl.hu.v3bfood_dishes.core.port.storage;

import java.util.List;
import java.util.UUID;

public interface StockRepository {
    List<UUID> findStockByIngredient(String ingredient);
}
