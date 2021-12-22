package nl.vkb.dishes.core.domain.event;
import lombok.Getter;
import nl.vkb.dishes.core.domain.Dish;

import java.util.UUID;

@Getter
public class DishAdded extends DishEvent {
    private final UUID dish;
    private final Dish dishObject;

    public DishAdded(UUID dish, Dish dishObject) {
        this.dish = dish;
        this.dishObject = dishObject;
    }

    @Override
    public String getEventKey() {
        return "dishes.added";
    }
}
