package nl.hu.v3bfood_dishes.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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
