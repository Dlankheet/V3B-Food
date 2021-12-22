package nl.vkb.order.core.domain.event;

import java.util.UUID;

public class OrderDenied extends OrderEvent{
    private final UUID order;
    private final String customer;

    public OrderDenied (UUID order, String customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public String getEventKey ( ) {
        return null;
    }

    public UUID getOrder ( ) {
        return order;
    }

    public String getCustomer ( ) {
        return customer;
    }
}
