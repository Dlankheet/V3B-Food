package nl.vkb.dishes.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class ListDishesById {
    private final List<UUID> dishIds;
}
