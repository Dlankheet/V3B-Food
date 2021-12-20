package com.order.core.domain.event;

import com.order.core.domain.OrderStatus;

import java.util.UUID;

public class OrderDelivered extends OrderEvent{
    private final UUID order;

    public OrderDelivered (UUID order) {
        this.order = order;
    }

    @Override
    public String getEventKey ( ) {
        return null;
    }

    public UUID getOrder ( ) {
        return order;
    }

}
