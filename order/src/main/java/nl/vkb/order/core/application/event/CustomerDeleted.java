package nl.vkb.order.core.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CustomerDeleted {
    private final UUID customerId;
}
