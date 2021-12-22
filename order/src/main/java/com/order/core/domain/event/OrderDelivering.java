package com.order.core.domain.event;

import java.util.UUID;

public class OrderDelivering extends OrderEvent{
    private final UUID order;
    private final UUID customer;


    public OrderDelivering (UUID order, UUID customer) {
        this.order = order;
        this.customer = customer;
    }

    @Override
    public String getEventKey ( ) {
        return "order.readyToDelivering";
    }

    public UUID getOrder ( ) {
        return order;
    }

    public UUID getCustomer ( ) {
        return customer;
    }
}
