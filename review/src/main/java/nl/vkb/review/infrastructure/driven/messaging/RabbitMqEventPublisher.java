package nl.vkb.review.infrastructure.driven.messaging;

import nl.vkb.review.core.domain.event.ReviewEvent;
import nl.vkb.review.core.port.messaging.ReviewEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements ReviewEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final String reviewExchange;

	
	public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String reviewExchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.reviewExchange = reviewExchange;
	}
	public void publish(ReviewEvent event) {
		this.rabbitTemplate.convertAndSend(reviewExchange, event.getEventKey(), event);
	}
}
