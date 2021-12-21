package nl.vkb.ingredients.core.domain;

import nl.vkb.ingredients.core.domain.event.StockUpdated;
import nl.vkb.ingredients.core.domain.exception.InvalidStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
	private Ingredient ingredient;
	@BeforeEach
	void init() {
		this.ingredient=new Ingredient("test",100);
	}
	@Test
	void addStockTest() {
		this.ingredient.addStock(100);
		assertEquals(200,this.ingredient.getStock());
		assertEquals(List.of(new StockUpdated(ingredient.getId(),200)),this.ingredient.getEvents());
	}
	@Test
	void addStockTooLowTest() {
		assertThrows(InvalidStockException.class,()->this.ingredient.addStock(-150));
	}
	@Test
	void setStock() {
		this.ingredient.setStock(150);
		assertEquals(150,this.ingredient.getStock());
		assertEquals(List.of(new StockUpdated(ingredient.getId(),150)),this.ingredient.getEvents());
	}
}
