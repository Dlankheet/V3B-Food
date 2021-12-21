package nl.vkb.ingredients.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SetAmount {
	private final UUID id;
	private final int amount;
}

