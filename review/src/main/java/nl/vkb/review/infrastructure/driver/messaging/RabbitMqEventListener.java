package nl.vkb.review.infrastructure.driver.messaging;

import nl.vkb.review.core.service.command.DeleteAllReviewsByCustomer;
import nl.vkb.review.core.service.ReviewCommandService;
import nl.vkb.review.infrastructure.driver.messaging.event.CustomerEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
	private final ReviewCommandService commandService;

	public RabbitMqEventListener(ReviewCommandService commandService) {
		this.commandService = commandService;
	}

	@RabbitListener(queues = "#{'${messaging.queue.customers}'}")
	void listen(CustomerEvent event) {
		if ("customers.deleted".equals(event.getEventKey())) {
			this.commandService.handle(
					new DeleteAllReviewsByCustomer(event.getCustomerId())
			);
		}
	}
}
