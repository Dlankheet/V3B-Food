package com.hu.bep3.vkb5.customer.core.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {
	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
