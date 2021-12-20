package com.order.core.application.command;

import java.util.Set;

public class RegisterOrder {
    private final String customer;
    private final Set<String > dishes;


    public RegisterOrder (String customer, Set<String> dishes) {
        this.customer = customer;
        this.dishes = dishes;
    }

    public Set<String> getDishes ( ) {
        return dishes;
    }

    public String getCustomer ( ) {
        return customer;
    }
}
