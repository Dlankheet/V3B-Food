package nl.vkb.ingredients.core.application.command;

import lombok.Data;

@Data
public class RegisterIngredient {
	private final String name;
	private final int stock;
}
