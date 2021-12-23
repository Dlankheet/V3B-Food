package nl.vkb.dishes.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class IngredientOrdered extends DishEvent{
	private final UUID ingredient;
	private final int amount;
	@Override
	public String getEventKey() {
		return "stock.ordered";
	}
}
