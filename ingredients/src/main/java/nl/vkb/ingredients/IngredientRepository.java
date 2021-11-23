package nl.vkb.ingredients;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
	Ingredient getIngredientById(String id);
}
