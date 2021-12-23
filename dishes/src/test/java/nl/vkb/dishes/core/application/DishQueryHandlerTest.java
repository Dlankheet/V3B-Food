package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.query.*;
import nl.vkb.dishes.core.application.results.OrderAvailableResult;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.domain.exceptions.DishNotFoundException;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DishQueryHandlerTest {

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
        when(dishRepository.findById(dish.getId())).thenReturn(java.util.Optional.ofNullable(dish));
        assertEquals(true, queryHandler.handle(new CheckAvailable(dish.getId())));
    }

    @Test
    @DisplayName("Test if Dish is not available in stock.")
    void isAvailableFalse() {
        StockResult stock = new StockResult(ingredient.getId(), "gehaktbal", 1);
        StockResult stock1 = new StockResult(ingredient1.getId(), "tomaat", 2);
        StockResult stock2 = new StockResult(ingredient2.getId(), "sla", 1);
        List<StockResult> stockList = List.of(stock, stock1, stock2);

        when(stockRepository.findIngredientByIds(anyList())).thenReturn(stockList);
        when(dishRepository.findById(dish.getId())).thenReturn(java.util.Optional.ofNullable(dish));

        assertEquals(false, queryHandler.handle(new CheckAvailable(dish.getId())));
    }

    @Test
    void checkStockHappyFlow ( ) {
        StockResult stockResult = new StockResult(ingredient.getId(), "gehaktbal", 2);
        when(stockRepository.findIngredientByIds(anyList())).thenReturn(List.of(stockResult));
        assertEquals(true, queryHandler.handle(new CheckStock(List.of(ingredient))));
    }
    @Test
    void checkStockFailFlow ( ) {
        StockResult stockResult = new StockResult(ingredient.getId(), "gehaktbal", 1);
        when(stockRepository.findIngredientByIds(anyList())).thenReturn(List.of(stockResult));
        assertEquals(false, queryHandler.handle(new CheckStock(List.of(ingredient))));
    }

    @Test
    void getListDishesByIdsHappyFlow ( ) {
        when(dishRepository.findById(dish.getId())).thenReturn(java.util.Optional.ofNullable(dish));
        ListDishesById dishes = new ListDishesById(List.of(dish.getId()));
        assertEquals(1, queryHandler.handle(dishes).size());
    }

    @Test
    void getListDishesByIdsFailFlow ( ) {
        when(dishRepository.findById(dish.getId())).thenThrow(DishNotFoundException.class);
        ListDishesById dishes = new ListDishesById(List.of(dish.getId()));
        assertThrows(DishNotFoundException.class, ()-> queryHandler.handle(dishes));
    }

    @Test
    void  sortDishesASC( ) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        when(dishRepository.findAll(sort)).thenReturn(List.of(dish));
        ListDishes listDishes = new ListDishes("name", "asc");
        assertEquals(1, queryHandler.handle(listDishes).size());

    }
    @Test
    void  sortDishesDESC( ) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        when(dishRepository.findAll(sort)).thenReturn(List.of(dish));
        ListDishes listDishes = new ListDishes("name", "desc");
        assertEquals(1, queryHandler.handle(listDishes).size());

    }

    @Test
    void checkOrderAvailabilityHappyFlow ( ) {
        when(dishRepository.findById(dish.getId())).thenReturn(java.util.Optional.ofNullable(dish));
        StockResult stock = new StockResult(ingredient.getId(), "gehaktbal", 10);
        StockResult stock1 = new StockResult(ingredient1.getId(), "tomaat", 10);
        StockResult stock2 = new StockResult(ingredient2.getId(), "sla", 10);
        when(stockRepository.findIngredientByIds(anyList())).thenReturn(List.of(stock, stock1, stock2));
        CheckOrderAvailability availability = new CheckOrderAvailability(List.of(dish.getId()));
        OrderAvailableResult orderAvailableResult = new OrderAvailableResult(false,List.of(dish.getId()),0.0);
        assertTrue(queryHandler.handle(availability).allAvailable);

    }
    @Test
    void checkOrderAvailabilityFailFlow ( ) {
        when(dishRepository.findById(dish.getId())).thenReturn(java.util.Optional.ofNullable(dish));
        StockResult stock = new StockResult(ingredient.getId(), "gehaktbal", 1);
        StockResult stock1 = new StockResult(ingredient1.getId(), "tomaat", 10);
        StockResult stock2 = new StockResult(ingredient2.getId(), "sla", 10);
        when(stockRepository.findIngredientByIds(anyList())).thenReturn(List.of(stock, stock1, stock2));
        CheckOrderAvailability availability = new CheckOrderAvailability(List.of(dish.getId()));
        OrderAvailableResult orderAvailableResult = new OrderAvailableResult(false,List.of(dish.getId()),0.0);
        assertFalse(queryHandler.handle(availability).allAvailable);

    }
}