package nl.vkb.ingredients;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {
    @RabbitListener(queues = "ingredient.subtract")
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
