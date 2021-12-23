package nl.vkb.dishes.core.domain.exceptions;

public class DishNotFoundException extends RuntimeException {
	public DishNotFoundException(String message) {
		super(message);
	}
}
