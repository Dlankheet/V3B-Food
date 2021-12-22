package nl.vkb.order.infrastructure.driver.web.response;

import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.OrderStatus;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private OrderStatus status;
    private UUID customer;
    private Set<UUID> dishes;
    private double price;

    public OrderDto(Order order){
        this.id = order.getId();
        this.status = order.getOrderStatus();
        this.customer = order.getCustomer();
        this.dishes = order.getDishes();
        this.price = order.getPrice();
    }
}
