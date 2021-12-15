package com.hu.bep3.vkb5.customer.infrastructure.driven.messaging;

import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class RabbitMqEventPublisher {
	private final RabbitTemplate rabbitTemplate;
	private final String foodExchange;

	public void publish(CustomerEvent event){
		this.rabbitTemplate.convertAndSend(foodExchange, event.getEventKey(), event);
	}
}