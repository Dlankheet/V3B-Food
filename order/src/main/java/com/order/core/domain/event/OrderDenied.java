package com.order.core.domain.event;

import com.order.core.domain.OrderStatus;

import java.util.UUID;

public class OrderDenied extends OrderEvent{
    private final UUID order;

    public OrderDenied (UUID order) {
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
