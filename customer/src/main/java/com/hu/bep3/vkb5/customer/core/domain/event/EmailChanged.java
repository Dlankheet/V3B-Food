package com.hu.bep3.vkb5.customer.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;
@Getter
@AllArgsConstructor
public class EmailChanged extends CustomerEvent {
	private final UUID customer;
	private final String newEmail;

	@Override
	public String getEventKey(){
		return "customers.email.changed";
	}
}