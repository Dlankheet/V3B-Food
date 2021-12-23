package nl.vkb.dishes.infrastructure.driven.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StockResultList {
    private List<StockResult> results;
}
