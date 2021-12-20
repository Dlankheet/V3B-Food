package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.command.AddAmount;
import nl.vkb.ingredients.core.application.command.RegisterIngredient;
import nl.vkb.ingredients.core.application.command.SetAmount;
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
		return this.publishEventsAndSave(ingredient);
	}

	public Ingredient handle(RegisterIngredient command) {
		Ingredient ingredient = new Ingredient(command.getName(), command.getStock());
		return this.publishEventsAndSave(ingredient);
	}

	public Ingredient handle(SetAmount command) {
		Ingredient ingredient = getIngredientById(command.getId());
		ingredient.setStock(command.getAmount());
		return this.publishEventsAndSave(ingredient);
	}

	private Ingredient getIngredientById(UUID id) {
		return this.repository.findById(id).orElseThrow(() -> new IngredientNotFound(id.toString()));
	}
	private Ingredient publishEventsAndSave(Ingredient ingredient) {
		List<StockEvent> events = ingredient.getEvents();
		events.forEach(eventPublisher::publish);
		ingredient.clearEvents();

		return this.repository.save(ingredient);
	}
}
