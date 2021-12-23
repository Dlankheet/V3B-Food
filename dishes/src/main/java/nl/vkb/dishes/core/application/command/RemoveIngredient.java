package nl.vkb.dishes.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;
@AllArgsConstructor
@Getter
public class RemoveIngredient {
    UUID dishId;
    UUID ingredientID;
}
