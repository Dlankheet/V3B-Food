package nl.vkb.ingredients;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class RestService {
	private final IngredientRepository repository;
	private final UpdateStockService updateStockService;
	public RestService(IngredientRepository repository, UpdateStockService updateStockService) {
		this.repository=repository;
		this.updateStockService = updateStockService;
	}

	@GetMapping("/{id}")
	public Ingredient get(@PathVariable String id) {
		return this.repository.findById(id).get();
	}
	@GetMapping("/ingredient/all")
	public List<Ingredient> getList() {
		return repository.findAll();
	}
	@PatchMapping("/{id}")
	public void patchIngredient(@PathVariable String id, @RequestBody int stock) {
		updateStockService.updateStorage(id,Action.SET,stock);
	}
	@PostMapping
	public String create(@RequestBody Ingredient ingredient){
		return repository.save(ingredient).id;
	}
}
