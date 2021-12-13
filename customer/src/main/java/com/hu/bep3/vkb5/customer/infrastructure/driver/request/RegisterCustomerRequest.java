package com.hu.bep3.vkb5.customer.infrastructure.driver.request;

import javax.validation.constraints.NotBlank;

public class RegisterCustomerRequest {
	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String email;
}
