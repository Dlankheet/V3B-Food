package nl.vkb.customer.core.port.messaging;

import nl.vkb.customer.core.domain.event.CustomerEvent;

public interface CustomerEventPublisher {
	void publish(CustomerEvent event);
}
