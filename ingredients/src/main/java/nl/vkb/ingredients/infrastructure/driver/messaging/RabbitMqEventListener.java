package nl.vkb.ingredients.infrastructure.driver.messaging;

import nl.vkb.ingredients.core.application.StockCommandHandler;
import nl.vkb.ingredients.core.application.command.AddAmount;
import nl.vkb.ingredients.infrastructure.driver.messaging.event.StockModifyEvent;
import nl.vkb.ingredients.infrastructure.driver.messaging.event.UnknownEventException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final StockCommandHandler commandHandler;

    public RabbitMqEventListener(StockCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.stock}'}")
    void listen(StockModifyEvent event) {
        if ("stock.ordered".equals(event.getEventKey())) {
            this.commandHandler.handle(
                    new AddAmount(event.getIngredient(), -event.getAmount())
            );
        } else throw new UnknownEventException(event.getEventKey() + " is not a known event!");
    }
}
