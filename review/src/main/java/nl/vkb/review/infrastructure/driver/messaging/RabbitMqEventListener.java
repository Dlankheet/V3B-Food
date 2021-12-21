package nl.vkb.review.infrastructure.driver.messaging;

import nl.vkb.review.core.Service.Command.MakeReview;
import nl.vkb.review.core.Service.ReviewCommandService;
import nl.vkb.review.infrastructure.driver.messaging.event.ReviewModifyEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
//	private final ReviewCommandService commandService;
//
//	public RabbitMqEventListener(ReviewCommandService commandService) {
//		this.commandService = commandService;
//	}
//
//	@RabbitListener(queues = "@{'${messaging.queue.review}'}")
//	void listen(ReviewModifyEvent event) {
//		switch (event.eventKey) {
//			case "review.create":
//				return;
//				this.commandService.handle(
//						new MakeReview(event.)
//				);
//		}
//	}
}
