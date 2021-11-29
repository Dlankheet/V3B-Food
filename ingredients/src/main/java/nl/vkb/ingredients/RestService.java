package nl.vkb.ingredients;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestService {
	private final IngredientRepository repository;
	public RestService(IngredientRepository repository) {
		this.repository=repository;
	}

	@GetMapping("/ingredient/{id}")
	public String get(@PathVariable String id) {
		return this.repository.findById(id).get().name;
	}
	@GetMapping("/ingredient/all")
	public List<Ingredient> getList() {
		return repository.findAll();
	}
}