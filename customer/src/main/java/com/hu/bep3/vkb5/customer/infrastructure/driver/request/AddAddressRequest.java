package com.hu.bep3.vkb5.customer.infrastructure.driver.request;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class AddAddressRequest {
	@NotBlank
	public String street;
	@NotBlank
	public int number;
	@NotBlank
	public String additionalLetter;
	@NotBlank
	public String postalCode;
}
