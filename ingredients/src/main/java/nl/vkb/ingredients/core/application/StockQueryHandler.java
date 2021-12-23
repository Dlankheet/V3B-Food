package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.query.GetIngredientById;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.core.ports.storage.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class StockQueryHandler {
	private final IngredientRepository repository;

	public StockQueryHandler(IngredientRepository repository) {
		this.repository = repository;
	}
	public List<Ingredient> handle() {
		return this.repository.findAll();
	}
	public Ingredient handle(GetIngredientById query) {
		return this.repository.findById(query.getId())
				.orElseThrow(() -> new IngredientNotFound(query.getId().toString()));
	}
	public List<Ingredient> handle(String filter) {
		String[] idStrings=filter.split(",");
		List<UUID> uuids=Arrays.stream(idStrings).map(UUID::fromString).toList();
		return (List<Ingredient>) repository.findAllById(uuids);
	}
}
