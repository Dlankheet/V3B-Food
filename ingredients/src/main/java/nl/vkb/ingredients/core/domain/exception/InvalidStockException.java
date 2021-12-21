package nl.vkb.ingredients.core.domain.exception;

public class InvalidStockException extends RuntimeException {
	public InvalidStockException(String message) {
		super(message);
	}
}
