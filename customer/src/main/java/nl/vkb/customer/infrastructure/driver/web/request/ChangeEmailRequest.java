package nl.vkb.customer.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class ChangeEmailRequest {
	@NotBlank
	public String newEmail;
}
