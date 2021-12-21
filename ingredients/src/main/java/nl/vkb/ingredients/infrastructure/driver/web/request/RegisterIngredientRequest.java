package nl.vkb.ingredients.infrastructure.driver.web.request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class RegisterIngredientRequest {
	@NotBlank
	private final String name;

	private final int stock;
}
