package com.order.infrastructure.driven.messaging;

import com.order.core.domain.event.OrderEvent;
import com.order.core.port.messaging.OrderEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String orderExchange;

    public RabbitMqEventPublisher (
            RabbitTemplate rabbitTemplate,
            String orderExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderExchange = orderExchange;
    }

    @Override
    public void orderPublish (OrderEvent event) {
        this.rabbitTemplate.convertAndSend(orderExchange, event.getEventKey(), event);
    }
}
