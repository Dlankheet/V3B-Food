package nl.vkb.ingredients.infrastructure.driver.web;

import nl.vkb.ingredients.core.application.StockCommandHandler;
import nl.vkb.ingredients.core.application.StockQueryHandler;
import nl.vkb.ingredients.core.application.command.DeleteIngredient;
import nl.vkb.ingredients.core.application.command.RegisterIngredient;
import nl.vkb.ingredients.core.application.command.SetAmount;
import nl.vkb.ingredients.core.application.query.GetIngredientById;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.infrastructure.driver.web.request.RegisterIngredientRequest;
import nl.vkb.ingredients.infrastructure.driver.web.request.SetAmountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
	private final StockCommandHandler commandHandler;
	private final StockQueryHandler queryHandler;

	public IngredientController(StockCommandHandler commandHandler, StockQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@GetMapping("/{id}")
	public Ingredient findIngredientById(@PathVariable UUID id) {
		return this.queryHandler.handle(new GetIngredientById(id));
	}

	@PostMapping
	public Ingredient create(@Valid @RequestBody RegisterIngredientRequest request) {
		return this.commandHandler.handle(new RegisterIngredient(request.getName(),request.getStock()));
	}

	@PostMapping("/{id}/set-amount")
	public Ingredient updateStock(@PathVariable UUID id, @Valid @RequestBody SetAmountRequest request) {
		return this.commandHandler.handle(new SetAmount(id, request.getStock()));
	}

	@DeleteMapping("/{id}")
	public void deleteIngredient(@PathVariable UUID id) {
		this.commandHandler.handle(new DeleteIngredient(id));
	}

	@GetMapping("/all")
	public List<Ingredient> getDishesFiltered(@RequestParam(required = false) String filter) {
		if(filter==null) return this.queryHandler.handle();
		else return Arrays.stream(filter.split(",")).map(id->queryHandler.handle(new GetIngredientById(UUID.fromString(id)))).toList();
	}
	@ExceptionHandler
	public ResponseEntity<Void> handleIngredientNotFound(IngredientNotFound exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
