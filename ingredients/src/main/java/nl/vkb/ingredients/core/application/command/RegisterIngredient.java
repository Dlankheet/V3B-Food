package nl.vkb.ingredients.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterIngredient {
	private final String name;
	private final int stock;
}
