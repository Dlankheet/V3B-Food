package nl.vkb.ingredients.infrastructure.driver.web;

import nl.vkb.ingredients.core.application.StockCommandHandler;
import nl.vkb.ingredients.core.application.StockQueryHandler;
import nl.vkb.ingredients.core.application.command.RegisterIngredient;
import nl.vkb.ingredients.core.application.query.GetIngredientById;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.infrastructure.driver.web.request.RegisterIngredientRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
	/*private final IngredientRepository repository;
	private final UpdateStockService updateStockService;
	public IngredientController(IngredientRepository repository, UpdateStockService updateStockService) {
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
		updateStockService.updateStorage(id, Action.SET,stock);
	}
	@PostMapping
	public String create(@RequestBody Ingredient ingredient){
		return repository.save(ingredient).id;
	}*/
	private final StockCommandHandler commandHandler;
	private final StockQueryHandler queryHandler;

	public IngredientController(StockCommandHandler commandHandler, StockQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@GetMapping("/{id}")
	public Ingredient findJobById(@PathVariable UUID id) {
		return this.queryHandler.handle(new GetIngredientById(id));
	}

	@PostMapping
	public Ingredient create(@Valid @RequestBody RegisterIngredientRequest request) {
		return this.commandHandler.handle(new RegisterIngredient(request.name,request.stock));
	}
	@ExceptionHandler
	public ResponseEntity<Void> handleIngredientNotFound(IngredientNotFound exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
