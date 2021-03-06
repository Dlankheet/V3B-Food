package nl.vkb.order.core.domain;


import nl.vkb.order.core.domain.event.*;
import nl.vkb.order.core.domain.exception.OrderStatusException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document(collection="order")
@Data
public class Order {
    @Id
    private UUID id;
    private boolean paid;
    private OrderStatus orderStatus;
    private double price;
    private UUID customer;
    private Set<UUID> dishes;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();


    public Order(UUID  customer, Set<UUID> dishes){
        this.id = UUID.randomUUID();
        this.paid = false;
        this.orderStatus = OrderStatus.PENDING;
        this.customer = customer;
        this.dishes = dishes;
        events.add(new OrderRegistered(this.id, customer));
    }
    public void acceptOrder(){
        checkStatus(OrderStatus.PENDING,OrderStatus.ACCEPTED );
        this.orderStatus = OrderStatus.ACCEPTED;
        events.add(new OrderAccepted(id, List.copyOf(dishes)));
    }

    public void denyOrder(){
        checkStatus(OrderStatus.PENDING,OrderStatus.DENIED );
        this.orderStatus = OrderStatus.DENIED;
        events.add(new OrderDenied(id, customer));
    }

    public void cancelOrder(){
        checkStatus(OrderStatus.ACCEPTED,OrderStatus.CANCELLED );
        this.orderStatus = OrderStatus.CANCELLED;
        events.add(new OrderCancelled(id, customer));
    }

    public void orderReadyToDelivered (){
        checkStatus(OrderStatus.ACCEPTED,OrderStatus.DELIVERING );
        this.orderStatus = OrderStatus.DELIVERING;
        events.add(new OrderDelivering(id, customer));
    }

    public void orderDelivered (){
        checkStatus(OrderStatus.DELIVERING,OrderStatus.DELIVERED );
        this.orderStatus = OrderStatus.DELIVERED;
        events.add(new OrderDelivered(id, customer));
    }
    private void checkStatus(OrderStatus beginStatus, OrderStatus endStatus){
        if (this.orderStatus != beginStatus)
            throw new OrderStatusException(this.orderStatus.name(),endStatus.name());
    }
    public void clearEvents() {
        this.events.clear();
    }

    public List<OrderEvent> listEvents() {
        return events;
    }

}
