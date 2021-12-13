package nl.vkb.ingredients;

import nl.vkb.ingredients.dto.ModifyIngredient;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QueueReceiver {
    private final UpdateStockService updateStockService;
    public QueueReceiver(UpdateStockService updateStockService) {
        this.updateStockService = updateStockService;
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
    @RabbitListener(queuesToDeclare = @Queue("ingredient.updateStock"))
    public void subtractReceive(ModifyIngredient modifyIngredient) {
        updateStockService.updateStorage(modifyIngredient.id,Action.ADD,modifyIngredient.action);
    }
}
