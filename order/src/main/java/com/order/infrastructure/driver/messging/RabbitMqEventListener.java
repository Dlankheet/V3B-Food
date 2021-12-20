package com.order.infrastructure.driver.messging;

import com.order.core.application.OrderCommandHandler;
import com.order.infrastructure.driver.messging.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

//    @RabbitListener(queues = "#{'${messaging.queue.order}'}")
//    void listen(OrderEvent orderEvent) {
//    }
}
