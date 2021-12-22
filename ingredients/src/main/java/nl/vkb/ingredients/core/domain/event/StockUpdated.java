package nl.vkb.ingredients.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class StockUpdated extends StockEvent {
    private final UUID ingredient;
    private final int stock;
    @Override
    public String getEventKey() {
        return "stock.updated";
    }
}
