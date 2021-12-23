package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.command.CreateDish;
import nl.vkb.dishes.core.application.command.DishOrdered;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.messaging.DishEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DishCommandHandlerTest {
	private DishCommandHandler handler;
	private DishRepository repository;
	private DishEventPublisher publisher;

	@BeforeEach
	void setup(){
		this.repository = mock(DishRepository.class);
		this.publisher = mock(DishEventPublisher.class);
		this.handler = new DishCommandHandler(repository, publisher);
	}

	@Test
	void createDishHappyflow(){
		Ingredient egg = new Ingredient(UUID.randomUUID(), 3);
		Ingredient onion = new Ingredient(UUID.randomUUID(), 1);
		List<Ingredient> ingredients = List.of(egg, onion);
		Dish mockedDish = new Dish("Scrambled Egg", 2.50, ingredients);
		CreateDish command = new CreateDish("Scrambled egg", 2.50, ingredients);
		when(repository.save(any(Dish.class)))
				.thenReturn(mockedDish);
		assertDoesNotThrow(()-> assertEquals(mockedDish, handler.handle(command)));
	}

	@Test
	void dishOrder(){
		Ingredient egg = new Ingredient(UUID.randomUUID(), 3);
		Ingredient onion = new Ingredient(UUID.randomUUID(), 1);
		List<Ingredient> ingredients = List.of(egg, onion);
		Dish mockedDish = new Dish("Scrambled Egg", 2.50, ingredients);
		DishOrdered command = new DishOrdered(UUID.randomUUID());
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedDish));
		assertDoesNotThrow(()-> {
			handler.handle(command);
			assertEquals(0, mockedDish.getEvents().size());
		});
	}
}