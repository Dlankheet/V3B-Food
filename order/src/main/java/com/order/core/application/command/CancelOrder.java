package com.order.core.application.command;

import java.util.UUID;

public class CancelOrder {
    private final UUID order;

    public CancelOrder (UUID order) {
        this.order = order;
    }

    public UUID getOrder ( ) {
        return order;
    }
}
