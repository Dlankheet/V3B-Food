package com.hu.bep3.vkb5.customer.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;
@Getter
@AllArgsConstructor
public class CustomerDeleted extends CustomerEvent{
	private final UUID customerId;

	@Override
	public String getEventKey() {
		return "customers.deleted";
	}
}
