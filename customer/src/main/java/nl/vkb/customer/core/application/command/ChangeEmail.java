package nl.vkb.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeEmail {
	private final UUID customerId;
	private final String newEmail;
}
