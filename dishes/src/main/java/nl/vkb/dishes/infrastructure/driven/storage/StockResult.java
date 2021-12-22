package nl.vkb.dishes.infrastructure.driven.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class StockResult {
    private final UUID id;
    private final String name;
    private final int stock;
}
