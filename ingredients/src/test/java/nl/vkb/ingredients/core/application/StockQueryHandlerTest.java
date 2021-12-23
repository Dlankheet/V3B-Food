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

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockQueryHandlerTest {
	@Autowired
	StockQueryHandler queryHandler;
	@Autowired
	private IngredientRepository repository;
	private Ingredient ingredient;
	private Ingredient ingredient2;
	private List<Ingredient> ingredientList;
	@BeforeEach
	void saveIngredients() {
		ingredient=repository.save(new Ingredient("eggs",100));
		ingredient2=repository.save(new Ingredient("eggs2", 200));
		ingredientList= new ArrayList<>(List.of(ingredient, ingredient2));
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
		repository.save(new Ingredient("melk",300));
		String filter=ingredient.getId()+","+ingredient2.getId();
		List<Ingredient> responseList=queryHandler.handle(filter);
		responseList.sort(Comparator.comparing(Ingredient::getId));
		ingredientList.sort(Comparator.comparing(Ingredient::getId));
		assertEquals(ingredientList,responseList);
	}
}
