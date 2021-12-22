package nl.vkb.order.infrastructure.driven.storage;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DishResult {
    public final boolean available;
    public final List<String> unavailableDishes;
    public final double price;
}
