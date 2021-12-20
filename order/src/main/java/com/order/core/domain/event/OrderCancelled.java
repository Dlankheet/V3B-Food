package com.order.core.domain.event;

import com.order.core.domain.OrderStatus;

import java.util.UUID;

public class OrderCancelled extends OrderEvent{
    private final UUID order;
    private final OrderStatus status;

    public OrderCancelled (UUID order, OrderStatus status) {
        this.order = order;
        this.status = status;
    }

    @Override
    public String getEventKey ( ) {
        return null;
    }

    public UUID getOrder ( ) {
        return order;
    }

    public OrderStatus getStatus ( ) {
        return status;
    }
}
