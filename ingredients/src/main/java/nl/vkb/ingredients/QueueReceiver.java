package nl.vkb.ingredients;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {
    private final IngredientRepository repository;

    public QueueReceiver(IngredientRepository repository) {
        this.repository = repository;
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @RabbitListener(queues = "ingredient.subtract")
    public void subtractReceive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
    @RabbitListener(queues = "ingredient.create")
    public void createReceive(Ingredient ingredient) {
        this.repository.save(ingredient);
    }
}
