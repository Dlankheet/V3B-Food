package nl.vkb.order.infrastructure.driver.messging;

import nl.vkb.order.core.application.OrderCommandHandler;
import nl.vkb.order.core.application.event.CustomerDeleted;
import nl.vkb.order.infrastructure.driver.messging.event.CustomerEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler commandHandler;

    public RabbitMqEventListener(OrderCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }


    @RabbitListener(queues = "#{'${messaging.queue.customers}'}")
    void listen(CustomerEvent event) {
        switch (event.eventKey) {
            case "customers.deleted":
                this.commandHandler.handle(new CustomerDeleted(event.customerId));
                System.out.print(event);
                break;
        }
    }
}
