package com.order.core.application.command;

import java.util.UUID;

public class AcceptOrder {
    private final UUID order;

    public AcceptOrder (UUID order) {
        this.order = order;
    }

    public UUID getOrder ( ) {
        return order;
    }
}
