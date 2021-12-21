package nl.vkb.review.infrastructure.driver.messaging;

import nl.vkb.review.core.Service.Command.DeleteAllReviewsByCustomer;
import nl.vkb.review.core.Service.ReviewCommandService;
import nl.vkb.review.infrastructure.driver.messaging.event.CustomerEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
	private final ReviewCommandService commandService;

	public RabbitMqEventListener(ReviewCommandService commandService) {
		this.commandService = commandService;
	}

	@RabbitListener(queues = "@{'${messaging.queue.customers}'}")
	void listen(CustomerEvent event) {
		switch (event.eventKey) {
			case "customers.deleted":
				this.commandService.handle(
						new DeleteAllReviewsByCustomer(event.eventId)
				);
		}
	}
}
