package com.hu.bep3.vkb5.customer.core.port.messaging;

import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;

public interface CustomerEventPublisher {
	void publish(CustomerEvent event);
}
