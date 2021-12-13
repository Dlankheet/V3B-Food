package nl.vkb.ingredients;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class UpdateStockService {
	private final RabbitTemplate rabbitTemplate;
	private final IngredientRepository repository;
	public UpdateStockService(@Lazy RabbitTemplate rabbitTemplate, IngredientRepository repository) {
		this.rabbitTemplate = rabbitTemplate;
		this.repository = repository;
	}

	public void updateStorage(String id, Action.ActionInterface action, int stock) {
		Ingredient ingredient=repository.findById(id).get();
		ingredient.stock=action.action(ingredient.stock,stock);
		repository.save(ingredient);
		rabbitTemplate.convertAndSend("updateStockRouting", ingredient);
	}
}
