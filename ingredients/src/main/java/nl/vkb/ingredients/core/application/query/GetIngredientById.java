package nl.vkb.ingredients.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class GetIngredientById {
	private final UUID id;
}
