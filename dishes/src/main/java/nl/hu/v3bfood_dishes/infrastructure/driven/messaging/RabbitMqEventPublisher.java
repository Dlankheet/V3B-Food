package nl.hu.v3bfood_dishes.infrastructure.driven.messaging;
import nl.hu.v3bfood_dishes.core.domain.event.DishEvent;
import nl.hu.v3bfood_dishes.core.port.messaging.DishEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DishEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String jobBoardExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String jobBoardExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.jobBoardExchange = jobBoardExchange;
    }

    public void publish(DishEvent event) {
        this.rabbitTemplate.convertAndSend(jobBoardExchange, event.getEventKey(), event);
    }
}
