package nl.vkb.customer.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class RegisterCustomerRequest {
	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String email;
}
