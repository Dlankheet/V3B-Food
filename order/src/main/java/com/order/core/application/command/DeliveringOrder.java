package com.order.core.application.command;

import java.util.UUID;

public class DeliveringOrder {
    private final UUID order;

    public DeliveringOrder (UUID order) {
        this.order = order;
    }

    public UUID getOrder ( ) {
        return order;
    }
}
