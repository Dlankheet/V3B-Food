package nl.vkb.ingredients.core.application.command;

import lombok.Data;

import java.util.UUID;

@Data
public class AddAmount {
	private final UUID id;
	private final int amount;
}
