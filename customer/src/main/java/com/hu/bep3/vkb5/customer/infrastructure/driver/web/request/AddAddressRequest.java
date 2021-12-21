package com.hu.bep3.vkb5.customer.infrastructure.driver.web.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class AddAddressRequest {
	@NotBlank
	public String street;
	@Digits(integer = 4, fraction = 0)
	public int number;
	@NotBlank
	public String additionalLetter;
	@NotBlank
	public String postalCode;
}
