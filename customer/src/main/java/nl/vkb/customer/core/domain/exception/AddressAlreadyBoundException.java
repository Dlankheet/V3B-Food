package nl.vkb.customer.core.domain.exception;

public class AddressAlreadyBoundException extends RuntimeException {
	public AddressAlreadyBoundException() {
		super("This address is already bound to this customer");
	}
}
