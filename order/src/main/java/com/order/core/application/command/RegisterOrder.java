package com.order.core.application.command;

import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class RegisterOrder {
    private final UUID customer;
    private final Set<UUID> dishes;


    public RegisterOrder (UUID customer, Set<UUID> dishes) {
        this.customer = customer;
        this.dishes = dishes;
    }
}
