package com.hu.bep3.vkb5.customer.core.application.command;

import com.hu.bep3.vkb5.customer.core.domain.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddAddress {
	private final UUID customerId;
	private final Address address;
}
