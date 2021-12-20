package com.order.core.application.command;

import java.util.UUID;

public class DenyOrder {
    private final UUID order;

    public DenyOrder (UUID order) {
        this.order = order;
    }

    public UUID getOrder ( ) {
        return order;
    }
}
