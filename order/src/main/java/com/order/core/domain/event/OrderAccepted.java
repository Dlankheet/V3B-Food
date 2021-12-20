package com.order.core.domain.event;

import com.order.core.domain.OrderStatus;

import java.util.UUID;

public class OrderAccepted extends OrderEvent{
    private final UUID order;

    public OrderAccepted (UUID order) {
        this.order = order;
    }

    @Override
    public String getEventKey ( ) {
        return "test.order";
    }

    public UUID getOrder ( ) {
        return order;
    }

}
