package com.hu.bep3.vkb5.customer.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@ToString
@EqualsAndHashCode
public class Address {
	private String street;
	private int number;
	private String additionalLetter;
	private String postalCode;
	@Indexed
	private Set<Customer> customers;

	public Address(String street, int number, String additionalLetter, String postalCode) {
		this.street = street;
		this.number = number;
		this.additionalLetter = additionalLetter;
		this.postalCode = postalCode;
		this.customers = new HashSet<>();
	}
}
