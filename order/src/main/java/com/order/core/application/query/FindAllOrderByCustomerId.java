package com.order.core.application.query;

import java.util.UUID;

public class FindAllOrderByCustomerId {
    private final String  customer;

    public FindAllOrderByCustomerId(String customer){
        this.customer = customer;
    }

    public String getCustomer ( ) {
        return customer;
    }
}
