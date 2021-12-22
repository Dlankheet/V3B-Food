package nl.vkb.ingredients.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class StockDeleted extends StockEvent {
	private final UUID ingredient;
	@Override
	public String getEventKey() {
		return "stock.deleted";
	}
}
