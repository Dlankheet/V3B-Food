package nl.vkb.ingredients.core.domain.event;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class StockUpdated extends StockEvent {
    private final UUID ingredient;
    private final int stock;

    public StockUpdated(UUID ingredient, int stock) {
        this.ingredient = ingredient;
        this.stock = stock;
    }

    @Override
    public String getEventKey() {
        return "stock.updated";
    }

    public UUID getIngredient() {
        return ingredient;
    }

    public int getStock() {
        return this.stock;
    }
}
