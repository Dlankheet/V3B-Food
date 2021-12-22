package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.command.AddAmount;
import nl.vkb.ingredients.core.application.command.RegisterIngredient;
import nl.vkb.ingredients.core.application.command.SetAmount;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.core.ports.storage.IngredientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockCommandHandlerTest {
	@Autowired
	private IngredientRepository repository;
	@Autowired
	private StockCommandHandler commandHandler;
	private Ingredient ingredient;
	@BeforeEach
	void saveIngredient() {
		ingredient=repository.save(new Ingredient("eggs",100));
	}
	@AfterEach
	void clearRepository(){
		repository.deleteAll();
	}
	@Test
	void registerIngredientHandleTest() {
		Ingredient ingredient=commandHandler.handle(new RegisterIngredient("eggs2",10));
		assertEquals(10, ingredient.getStock(), "amount should be set.");
		assertEquals("eggs2", ingredient.getName(), "name should be set.");
		Ingredient fromRepo=repository.findById(ingredient.getId()).get();
		assertEquals(ingredient, fromRepo, "should be persisted correctly.");
	}

	@Test
	void addAmountHandleTest() {
		commandHandler.handle(new AddAmount(ingredient.getId(),50));
		assertEquals(150,repository.findById(ingredient.getId()).get().getStock());
	}
	@Test
	void unknownIngredientTest() {
		AddAmount addAmount=new AddAmount(UUID.randomUUID(),10);
		assertThrows(IngredientNotFound.class,()->commandHandler.handle(addAmount));
	}

	@Test
	void setAmountHandleRequest() {
		commandHandler.handle(new SetAmount(ingredient.getId(),50));
		assertEquals(50,repository.findById(ingredient.getId()).get().getStock());
	}
}
