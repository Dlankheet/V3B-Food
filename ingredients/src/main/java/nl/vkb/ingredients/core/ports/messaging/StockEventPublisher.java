package nl.vkb.ingredients.core.ports.messaging;

import nl.vkb.ingredients.core.domain.event.StockEvent;

public interface StockEventPublisher {
	void publish(StockEvent event);
}
