package nl.vkb.dishes.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CheckAvailable {
    private final UUID id;
}
