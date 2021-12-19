package nl.vkb.ingredients.infrastructure.driver.web.request;
import javax.validation.constraints.NotBlank;

public class RegisterIngredientRequest {
	@NotBlank
	public String name;

	@NotBlank
	public int stock;
}
