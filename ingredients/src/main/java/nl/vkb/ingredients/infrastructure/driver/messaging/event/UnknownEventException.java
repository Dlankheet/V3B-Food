package nl.vkb.ingredients.infrastructure.driver.messaging.event;

public class UnknownEventException extends RuntimeException {
	public UnknownEventException(String message) {
		super(message);
	}
}
