package nl.vkb.order.infrastructure.driver.messging;

import nl.vkb.order.core.application.OrderCommandHandler;
import nl.vkb.order.infrastructure.driver.messging.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order}'}")
    void listen(OrderEvent orderEvent) {
    }
}
