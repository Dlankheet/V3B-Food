package nl.vkb.order.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeliveredOrder {
    private final UUID order;
}
