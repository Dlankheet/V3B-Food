package com.hu.bep3.vkb5.customer.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
@EqualsAndHashCode
public class Address {
	private final String street;
	private final int number;
	private final String additionalLetter;
	private final String postalCode;

	public Address(String street, int number, String additionalLetter, String postalCode) {
		this.street = street;
		this.number = number;
		this.additionalLetter = additionalLetter;
		this.postalCode = postalCode;
	}
}
