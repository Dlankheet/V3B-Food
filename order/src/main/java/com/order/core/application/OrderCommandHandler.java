package com.order.core.application;

import com.order.core.application.command.*;
import com.order.core.domain.Order;
import com.order.core.domain.event.OrderEvent;
import com.order.core.domain.exception.OrderNotFoundException;
import com.order.core.port.data.OrderRepository;
import com.order.core.port.messaging.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public OrderCommandHandler (OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }
    public Order handle(RegisterOrder command){
        Order order = new Order(command.getCustomer(), command.getDishes());
        
        this.repository.save(order);
        return order;
    }

    public Order handle(AcceptOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.acceptOrder();
        this.repository.save(order);
        return order;
    }

    public Order handle(CancelOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.cancelOrder();
        this.repository.save(order);
        return order;
    }

    public Order handle(DeliveringOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.orderReadyToDelivered();
        this.repository.save(order);
        return order;
    }

    public Order handle(DenyOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.denyOrder();
        this.repository.save(order);
        return order;
    }


    private Order getOrderById(UUID id) {
    return this.repository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id.toString()));
    }

    private void publishEventsFor(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::orderPublish);
        order.clearEvents();
    }
}
