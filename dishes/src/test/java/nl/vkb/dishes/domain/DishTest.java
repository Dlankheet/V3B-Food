package nl.vkb.dishes.domain;

import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DishTest {
    private Dish dish;
    private UUID random;

    @BeforeEach
    void setupTest() {
        this.random = UUID.randomUUID();
        this.dish = new Dish("Broodje Bal", 12, new ArrayList<>(List.of(new Ingredient(random, 2))));
    }

    @Test
    @DisplayName("Add ingredient")
    void addIngredient() {
        this.dish.addIngredient(new Ingredient(UUID.randomUUID(), 4));
        assertEquals(2, this.dish.getIngredients().size());
    }

    @Test
    @DisplayName("Remove ingredient")
    void removeIngredient() {
        this.dish.removeIngredient(random);
        assertEquals(0, this.dish.getIngredients().size());
    }

    @Test
    @DisplayName("Remove ingredient that doesnt exist")
    void removeIngredientThatDoesntExsist() {
        this.dish.removeIngredient(random);
        this.dish.removeIngredient(random);
        assertEquals(0, this.dish.getIngredients().size());
    }
}
