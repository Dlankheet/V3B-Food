package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.command.AddAmount;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.event.StockEvent;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.core.ports.messaging.StockEventPublisher;
import nl.vkb.ingredients.core.ports.storage.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockCommandHandler {
	private final IngredientRepository repository;
	private final StockEventPublisher eventPublisher;

	public StockCommandHandler(IngredientRepository repository, StockEventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
	}

	public Ingredient handle(AddAmount command) {
		Ingredient ingredient = this.getIngredientById(command.getId());
		ingredient.addStock(command.getAmount());
		this.publishEventsAndSave(ingredient);
		return ingredient;
	}

	private Ingredient getIngredientById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new IngredientNotFound(id.toString()));
	}
	private void publishEventsAndSave(Ingredient ingredient) {
		List<StockEvent> events = ingredient.listEvents();
		events.forEach(eventPublisher::publish);
		ingredient.clearEvents();

		this.repository.save(ingredient);
	}
}
