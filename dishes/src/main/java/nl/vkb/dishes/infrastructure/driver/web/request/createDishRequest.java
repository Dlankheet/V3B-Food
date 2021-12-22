package nl.vkb.dishes.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Ingredient;

import java.util.List;

@AllArgsConstructor
@Getter
public class createDishRequest {
    private final String title;
    private final double price;
    private final List<Ingredient> ingredients;
}
