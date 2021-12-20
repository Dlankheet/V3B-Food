package nl.vkb.review.core.port.messaging;

import nl.vkb.review.core.domain.event.ReviewEvent;

public interface ReviewEventPublisher {
	void publish(ReviewEvent event);
}
