package com.order.core.port.messaging;

import com.order.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void orderPublish(OrderEvent event);
}
