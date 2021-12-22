package com.hu.bep3.vkb5.customer.infrastructure.driver.messaging;

import com.hu.bep3.vkb5.customer.core.application.CustomerCommandHandler;
import com.hu.bep3.vkb5.customer.core.application.command.OrderFood;
import com.hu.bep3.vkb5.customer.core.application.command.ReviewOrder;
import com.hu.bep3.vkb5.customer.infrastructure.driver.messaging.event.OrderEvent;
import com.hu.bep3.vkb5.customer.infrastructure.driver.messaging.event.ReviewEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMqEventListener {
	private final CustomerCommandHandler commandHandler;

	@RabbitListener(queues = "#{'${messaging.queue.orders}'}")
	public void listen(OrderEvent event){
		switch (event.getEventKey()) {
			case "orders.new" -> this.commandHandler.handle(
					new OrderFood(event.getOrderId(), event.getCustomerId())
			);
			default -> System.out.println(String.format("No listener case for: %s", event.getEventKey()));
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.reviews}'}")
	public void listen(ReviewEvent event){
		switch (event.getEventKey()) {
			case "event.review.created" -> this.commandHandler.handle(
					new ReviewOrder(event.getCustomerId(), event.getReviewId())
			);
			default -> System.out.println(String.format("No listener case for: %s", event.getEventKey()));
		}
	}
}
