package com.order.infrastructure.driver.web.response;

import com.order.core.domain.Order;
import com.order.core.domain.OrderStatus;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private OrderStatus status;
    private String customer;
    private Set<String> dishes;

    public OrderDto(Order order){
        this.id = order.getId();
        this.status = order.getOrderStatus();
        this.customer = order.getCustomer();
        this.dishes = order.getDishes();
    }
}
