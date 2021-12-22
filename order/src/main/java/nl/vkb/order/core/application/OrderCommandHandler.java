package nl.vkb.order.core.application;
import nl.vkb.order.core.application.command.*;
import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.event.OrderEvent;
import nl.vkb.order.core.domain.exception.OrderNotFoundException;
import nl.vkb.order.core.port.data.DishRepository;
import nl.vkb.order.core.port.data.OrderRepository;
import nl.vkb.order.core.port.messaging.OrderEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderCommandHandler {
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;
    private final DishRepository dishGateway;

    public OrderCommandHandler (OrderRepository repository, OrderEventPublisher eventPublisher, DishRepository dishGateway) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.dishGateway = dishGateway;
    }
    public Order handle(RegisterOrder command){
        Order order = new Order(command.getCustomer(), command.getDishes());
        String dishes = String.join(",", new ArrayList<>(order.getDishes()));
        order.setPrice(dishGateway.getPriceByDishes(dishes));
        this.publishEventsFor(order);
        this.repository.save(order);
        return order;
    }

    public Order handle(AcceptOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.acceptOrder();
        this.publishEventsFor(order);
        this.repository.save(order);
        return order;
    }

    public Order handle(CancelOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.cancelOrder();
        this.publishEventsFor(order);
        this.repository.save(order);
        return order;
    }

    public Order handle(DeliveringOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.orderReadyToDelivered();
        this.publishEventsFor(order);
        this.repository.save(order);
        return order;
    }

    public Order handle(DenyOrder command) {
        Order order = this.getOrderById(command.getOrder());
        order.denyOrder();
        this.publishEventsFor(order);
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
