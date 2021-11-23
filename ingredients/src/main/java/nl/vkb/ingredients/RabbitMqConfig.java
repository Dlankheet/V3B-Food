package nl.vkb.ingredients;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue ingredientSubtract() {
        return new Queue("ingredient.subtract", true, false, false);
    }
    @Bean
    public Queue createIngredient() {
        return new Queue("ingredient.create", true, false, false);
    }
}
