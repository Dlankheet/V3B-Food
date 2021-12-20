package com.order.core.domain.event;

import com.order.core.domain.OrderStatus;

import java.util.UUID;

public class OrderAccepted extends OrderEvent{
    private final UUID order;
    private final OrderStatus status;

    public OrderAccepted (UUID order, OrderStatus status) {
        this.order = order;
        this.status = status;
    }

    @Override
    public String getEventKey ( ) {
        return "test.order";
    }

    public UUID getOrder ( ) {
        return order;
    }

    public OrderStatus getStatus ( ) {
        return status;
    }
}
