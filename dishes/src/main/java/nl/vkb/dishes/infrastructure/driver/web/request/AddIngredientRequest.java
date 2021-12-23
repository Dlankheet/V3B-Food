package nl.vkb.dishes.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Ingredient;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddIngredientRequest {
    private final UUID id;
    private final int amount;
}
