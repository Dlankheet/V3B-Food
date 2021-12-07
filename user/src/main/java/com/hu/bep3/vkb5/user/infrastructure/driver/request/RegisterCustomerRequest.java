package com.hu.bep3.vkb5.user.infrastructure.driver.request;

import javax.validation.constraints.NotBlank;

public class RegisterCustomerRequest {
	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String email;
}
