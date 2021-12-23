package nl.vkb.ingredients.core.application;

import nl.vkb.ingredients.core.application.query.GetIngredientById;
import nl.vkb.ingredients.core.domain.Ingredient;
import nl.vkb.ingredients.core.domain.exception.IngredientNotFound;
import nl.vkb.ingredients.core.ports.storage.IngredientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

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
		assertEquals(ingredientList,queryHandler.handle());
	}
	@Test
	void getIngredientHandleTest() {
		assertEquals(ingredient,queryHandler.handle(new GetIngredientById(ingredient.getId())));
	}
	@Test
	void getIngredientHandleNotFoundTest() {
		GetIngredientById getIngredient=new GetIngredientById(UUID.randomUUID());
		assertThrows(IngredientNotFound.class,()->queryHandler.handle(getIngredient));
	}
	@Test
	void getMultiple() {
		Ingredient ingredient3=repository.save(new Ingredient("melk",300));
		String filter=ingredientList.stream()
				.map(Ingredient::getId)
				.map(UUID::toString)
				.collect(Collectors.joining(","));
		List<String> responseStrings=new ArrayList<String>(queryHandler.handle(filter).stream().map(Ingredient::getId).map(UUID::toString).toList());
		List<String> expectedStrings=new ArrayList<String>(ingredientList.stream().map(Ingredient::getId).map(UUID::toString).toList());
		Collections.sort(responseStrings);
		Collections.sort(expectedStrings);
		assertEquals(expectedStrings,responseStrings);
	}
}
