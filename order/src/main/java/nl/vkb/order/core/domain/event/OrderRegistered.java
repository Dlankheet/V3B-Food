package nl.vkb.order.core.domain.event;

import java.util.UUID;

public class OrderRegistered extends OrderEvent{
    private final UUID order;
    private final UUID customer;

    public OrderRegistered (UUID order, UUID customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public String getEventKey ( ) {
        return "order.registered";
    }

    public UUID getOrder ( ) {
        return order;
    }

    public UUID getCustomer ( ) {
        return customer;
    }
}
