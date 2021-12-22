package nl.vkb.order.core.port.messaging;

import nl.vkb.order.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void orderPublish(OrderEvent event);
}
