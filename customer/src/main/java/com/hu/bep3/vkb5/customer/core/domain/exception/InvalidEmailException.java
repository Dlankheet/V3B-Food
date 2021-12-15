package com.hu.bep3.vkb5.customer.core.domain.exception;

public class InvalidEmailException extends RuntimeException {
	public InvalidEmailException() {
		super("This is not a valid email address");
	}
}
