package nl.vkb.dishes.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Ingredient;
import java.util.List;

@Getter
@AllArgsConstructor
public class createDish{
    private final String title;
    private final double price;
    private final List<Ingredient> ingredients;
}
