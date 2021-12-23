package nl.vkb.order.core.domain.event;

import java.util.UUID;

public class OrderRrgistered extends OrderEvent{
    private final UUID order;
    private final UUID customer;

    public OrderRrgistered (UUID order, UUID customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public String getEventKey ( ) {
        return "order.denied";
    }

    public UUID getOrder ( ) {
        return order;
    }

    public UUID getCustomer ( ) {
        return customer;
    }
}
