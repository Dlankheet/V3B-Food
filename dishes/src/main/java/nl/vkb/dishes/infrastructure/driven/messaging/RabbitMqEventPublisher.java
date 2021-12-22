package nl.vkb.dishes.infrastructure.driven.messaging;
import nl.vkb.dishes.core.domain.event.DishEvent;
import nl.vkb.dishes.core.port.messaging.DishEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DishEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String foodExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String foodExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.foodExchange = foodExchange;
    }

    public void publish(DishEvent event) {
        this.rabbitTemplate.convertAndSend(foodExchange, event.getEventKey(), event);
    }
}
