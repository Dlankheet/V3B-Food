package nl.vkb.order.core.domain.event;

import lombok.Getter;

import java.util.List;
import java.util.UUID;
@Getter
public class OrderAccepted extends OrderEvent{
    private final UUID order;
    private List<UUID> dishes;

    public OrderAccepted (UUID order, List<UUID> dishes) {
        this.order = order;
        this.dishes = dishes;
    }

    @Override
    public String getEventKey ( ) {
        return "order.accepted";
    }
}
