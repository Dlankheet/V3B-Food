package nl.vkb.ingredients.core.domain.exception;

public class IngredientNotFound extends RuntimeException {
	public IngredientNotFound(String message) {
		super(message);
	}
}
