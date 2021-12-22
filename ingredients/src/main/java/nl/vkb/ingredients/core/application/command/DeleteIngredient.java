package nl.vkb.ingredients.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteIngredient {
	private UUID id;
}
