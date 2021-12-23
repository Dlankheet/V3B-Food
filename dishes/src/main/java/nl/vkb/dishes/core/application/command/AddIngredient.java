package nl.vkb.dishes.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Ingredient;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddIngredient {
    UUID dishId;
    Ingredient ingredient;
}
