package nl.vkb.dishes.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Ingredient;

import java.util.List;
@AllArgsConstructor
@Getter
public class CheckStock {
    List<Ingredient> ingredients;
}
