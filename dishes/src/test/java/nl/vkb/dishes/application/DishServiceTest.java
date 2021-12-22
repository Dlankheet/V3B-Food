package nl.vkb.dishes.application;

import nl.vkb.dishes.core.application.DishCommandHandler;
import nl.vkb.dishes.core.application.DishQueryHandler;
import nl.vkb.dishes.core.application.query.checkAvailable;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DishServiceTest {
    private DishRepository dishRepository;
    private StockRepository stockRepository;
    private DishQueryHandler queryHandler;
    private Dish dish;
    private Ingredient ingredient;
    private Ingredient ingredient1;
    private Ingredient ingredient2;


    @BeforeEach
    void setupTest(){
        this.dishRepository = mock(DishRepository.class);
        this.stockRepository = mock(StockRepository.class);
        this.queryHandler = new DishQueryHandler(dishRepository, stockRepository);

        ingredient = new Ingredient(UUID.randomUUID(), 2);
        ingredient1 = new Ingredient(UUID.randomUUID(), 5);
        ingredient2 = new Ingredient(UUID.randomUUID(), 1);
        List<Ingredient> ingredients = List.of(ingredient, ingredient1, ingredient2);

        dish = new Dish("Broodje Bal", 10.20, ingredients);
        dish.setId("dishid");
    }

    @Test
    @DisplayName("Test if Dish is available in stock.")
    void isAvailableTrue(){
        StockResult stock = new StockResult(ingredient.getId(), "gehaktbal", 3);
        StockResult stock1 = new StockResult(ingredient1.getId(), "tomaat", 10);
        StockResult stock2 = new StockResult(ingredient2.getId(), "sla", 10);
        when(stockRepository.findIngredientById(ingredient.getId())).thenReturn(stock);
        when(stockRepository.findIngredientById(ingredient1.getId())).thenReturn(stock1);
        when(stockRepository.findIngredientById(ingredient2.getId())).thenReturn(stock2);
        when(dishRepository.findById("dishid")).thenReturn(java.util.Optional.ofNullable(dish));
        assertEquals(true, queryHandler.handle(new checkAvailable("dishid")));
    }

    @Test
    @DisplayName("Test if Dish is not available in stock.")
    void isAvailableFalse(){
        StockResult stock = new StockResult(ingredient.getId(), "gehaktbal", 1);
        StockResult stock1 = new StockResult(ingredient1.getId(), "tomaat", 2);
        StockResult stock2 = new StockResult(ingredient2.getId(), "sla", 1);
        when(stockRepository.findIngredientById(ingredient.getId())).thenReturn(stock);
        when(stockRepository.findIngredientById(ingredient1.getId())).thenReturn(stock1);
        when(stockRepository.findIngredientById(ingredient2.getId())).thenReturn(stock2);
        when(dishRepository.findById("dishid")).thenReturn(java.util.Optional.ofNullable(dish));
        assertEquals(false, queryHandler.handle(new checkAvailable("dishid")));
    }
}
