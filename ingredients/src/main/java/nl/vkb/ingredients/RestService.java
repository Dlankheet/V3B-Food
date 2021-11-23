package nl.vkb.ingredients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService {
	private static IngredientRepository repository;
	public RestService(IngredientRepository repository) {
		this.repository=repository;
	}

	@GetMapping("/ingredient/{id}")
	public String get(@PathVariable String id) {
		return this.repository.getIngredientById(id).name;
	}
}
