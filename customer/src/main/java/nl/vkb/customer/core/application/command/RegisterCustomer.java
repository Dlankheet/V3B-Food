package nl.vkb.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCustomer {
	private final String firstName;
	private final String lastName;
	private final String email;
}
