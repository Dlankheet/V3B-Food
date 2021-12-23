package nl.vkb.dishes.infrastructure.driver.messaging;

import nl.vkb.dishes.core.application.DishCommandHandler;
import nl.vkb.dishes.core.application.command.DishOrdered;
import nl.vkb.dishes.infrastructure.driver.messaging.event.OrderAcceptedEvent;
import nl.vkb.dishes.infrastructure.driver.messaging.event.UnknownEventException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMqEventListener {
    private final DishCommandHandler commandHandler;

    public RabbitMqEventListener(DishCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.dishes}'}")
    void listen(OrderAcceptedEvent event) {
        if ("order.accepted".equals(event.getEventKey())) {
            for(UUID dish:event.getDishes()) {
                commandHandler.handle(new DishOrdered(dish));
            }
        } else throw new UnknownEventException(event.getEventKey() + " is not a known event!");
    }
}
