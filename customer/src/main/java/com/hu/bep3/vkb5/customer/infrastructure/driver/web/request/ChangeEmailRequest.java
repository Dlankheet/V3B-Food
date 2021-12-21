package com.hu.bep3.vkb5.customer.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangeEmailRequest {
	@NotBlank
	public String newEmail;
}
