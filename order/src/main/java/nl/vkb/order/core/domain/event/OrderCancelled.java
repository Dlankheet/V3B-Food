package nl.vkb.order.core.domain.event;

import java.util.UUID;

public class OrderCancelled extends OrderEvent{
    private final UUID order;
    private final UUID customer;

    public OrderCancelled (UUID order, UUID customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public String getEventKey ( ) {
        return "order.cancelled";
    }

    public UUID getOrder ( ) {
        return order;
    }

    public UUID getCustomer ( ) {
        return customer;
    }
}
