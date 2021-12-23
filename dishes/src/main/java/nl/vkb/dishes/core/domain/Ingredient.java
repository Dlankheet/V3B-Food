package nl.vkb.dishes.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@ToString
@Getter
@Setter
public class Ingredient {
    private UUID id;
    private int amount;

    public Ingredient(UUID id, int amount) {
        this.id = id;
        this.amount = amount;
    }

}
