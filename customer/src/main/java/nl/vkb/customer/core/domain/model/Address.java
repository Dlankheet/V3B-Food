package nl.vkb.customer.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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
