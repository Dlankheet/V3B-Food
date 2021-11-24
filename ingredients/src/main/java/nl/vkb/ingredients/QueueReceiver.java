package nl.vkb.ingredients;

import nl.vkb.ingredients.dto.ModifyIngredient;
import org.springframework.amqp.rabbit.annotation.Queue;
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
    @RabbitListener(queuesToDeclare = @Queue("ingredient.subtract"))
    public void subtractReceive(ModifyIngredient modifyIngredient) {
        Ingredient ingredient=repository.findById(modifyIngredient.id).get();
        ingredient.stock+= modifyIngredient.action;
        repository.save(ingredient);
    }
}
