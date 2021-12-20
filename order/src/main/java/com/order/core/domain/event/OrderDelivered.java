package com.order.core.domain.event;

import java.util.UUID;

public class OrderDelivered extends OrderEvent{
    private final UUID order;
    private final String customer;

    public OrderDelivered (UUID order, String customer) {
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
