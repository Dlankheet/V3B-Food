package com.order.infrastructure.driver.messging.event;

import com.order.core.domain.OrderStatus;

import java.util.Set;
import java.util.UUID;

public class OrderEvent {
    private UUID id;
    private boolean paid;
    private OrderStatus orderStatus;
    private String customer;
    private Set<String> dishes;
}
