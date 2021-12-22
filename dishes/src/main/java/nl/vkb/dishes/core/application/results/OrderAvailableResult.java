package nl.vkb.dishes.core.application.results;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class OrderAvailableResult {
    public final boolean allAvailable;
    public final List<UUID> unavailableDishes;
    public final double totalPrice;
}
