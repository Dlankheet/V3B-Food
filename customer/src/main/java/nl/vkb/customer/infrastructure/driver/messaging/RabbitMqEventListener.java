package nl.vkb.customer.infrastructure.driver.messaging;

import nl.vkb.customer.core.application.CustomerCommandHandler;
import nl.vkb.customer.core.application.command.OrderFood;
import nl.vkb.customer.core.application.command.RemoveReview;
import nl.vkb.customer.core.application.command.ReviewOrder;
import nl.vkb.customer.infrastructure.driver.messaging.event.OrderEvent;
import nl.vkb.customer.infrastructure.driver.messaging.event.ReviewEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMqEventListener {
	private final CustomerCommandHandler commandHandler;

	@RabbitListener(queues = "#{'${messaging.queue.orders}'}")
	public void listen(OrderEvent event){
		if ("order.registered".equals(event.getEventKey())) {
			this.commandHandler.handle(
					new OrderFood(event.getOrder(), event.getCustomer())
			);
		}
	}

	@RabbitListener(queues = "#{'${messaging.queue.reviews}'}")
	public void listen(ReviewEvent event){
		if ("event.review.created".equals(event.getEventKey())) {
			this.commandHandler.handle(
					new ReviewOrder(event.getCustomer(), event.getReview())
			);
		}
		if ("event.review.deleted".equals(event.getEventKey())) {
			this.commandHandler.handle(
					new RemoveReview(event.getReview())
			);
		}
	}
}
