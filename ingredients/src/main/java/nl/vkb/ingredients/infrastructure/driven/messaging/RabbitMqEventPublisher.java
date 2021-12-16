package nl.vkb.ingredients.infrastructure.driven.messaging;

import nl.vkb.ingredients.core.domain.event.StockEvent;
import nl.vkb.ingredients.core.ports.messaging.StockEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements StockEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final String ingredientExchange;

	public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String ingredientExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.ingredientExchange = ingredientExchange;
	}

	public void publish(StockEvent event) {
		this.rabbitTemplate.convertAndSend(ingredientExchange, event.getEventKey(), event);
	}
}
