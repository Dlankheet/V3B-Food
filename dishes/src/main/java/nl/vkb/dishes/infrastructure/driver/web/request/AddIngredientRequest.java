package nl.vkb.dishes.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class AddIngredientRequest {
    private final UUID id;
    private final int amount;
}
