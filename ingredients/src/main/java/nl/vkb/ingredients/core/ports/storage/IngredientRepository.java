package nl.vkb.ingredients.core.ports.storage;

import nl.vkb.ingredients.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {}
