package com.hu.bep3.vkb5.customer.core.domain.exception;

public class AddressAlreadyBoundException extends RuntimeException {
	public AddressAlreadyBoundException() {
		super("This address is already bound to this customer");
	}
}
