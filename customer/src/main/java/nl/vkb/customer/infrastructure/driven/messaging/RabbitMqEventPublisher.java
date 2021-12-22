package nl.vkb.customer.infrastructure.driven.messaging;

import nl.vkb.customer.core.domain.event.CustomerEvent;
import nl.vkb.customer.core.port.messaging.CustomerEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqEventPublisher implements CustomerEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final String foodExchange;

	public void publish(CustomerEvent event){
		this.rabbitTemplate.convertAndSend(foodExchange, event.getEventKey(), event);
	}
}
