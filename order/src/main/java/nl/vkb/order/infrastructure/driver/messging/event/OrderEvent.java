package nl.vkb.order.infrastructure.driver.messging.event;

import nl.vkb.order.core.domain.OrderStatus;

import java.util.Set;
import java.util.UUID;

public class OrderEvent {
    private UUID id;
    private boolean paid;
    private OrderStatus orderStatus;
    private String customer;
    private Set<String> dishes;
}
