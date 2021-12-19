package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.query.GetAllIngredients;
import nl.vkb.ingredients.core.application.query.GetIngredientById;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.ports.storage.IngredientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockQueryHandlerTest {
	@Autowired
	StockQueryHandler queryHandler;
	@Autowired
	private IngredientRepository repository;
	private Ingredient ingredient;
	private List<Ingredient> ingredientList;
	@BeforeEach
	void saveIngredients() {
		ingredient=repository.save(new Ingredient("eggs",100));
		ingredientList=List.of(ingredient,repository.save(new Ingredient("eggs2",200)));
	}
	@AfterEach
	void clearRepository(){
		repository.deleteAll();
	}

	@Test
	void getAllIngredientsHandleTest() {
		assertEquals(ingredientList,queryHandler.handle(new GetAllIngredients()));
	}
	@Test
	void getIngredientHandleTest() {
		assertEquals(ingredient,queryHandler.handle(new GetIngredientById(ingredient.getId())));
	}
}
