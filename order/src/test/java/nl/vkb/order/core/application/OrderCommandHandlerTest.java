package nl.vkb.order.core.application;

import nl.vkb.order.core.application.command.*;
import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.OrderStatus;
import nl.vkb.order.core.domain.event.OrderEvent;
import nl.vkb.order.core.port.data.DishRepository;
import nl.vkb.order.core.port.data.OrderRepository;
import nl.vkb.order.core.port.messaging.OrderEventPublisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderCommandHandlerTest {
    @Autowired
    private OrderRepository repository;
    private OrderCommandHandler commandHandler;
    private Order order;

    @BeforeEach
    void setup(){
        Set<UUID> dishes = new HashSet<>();
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52be"));
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52ba"));
        order = new Order(UUID.randomUUID(), dishes);
        OrderEventPublisher orderEventPublisher = Mockito.mock(OrderEventPublisher.class);
        doNothing().when(orderEventPublisher).orderPublish(any(OrderEvent.class));

        DishRepository dishRepository = Mockito.mock(DishRepository.class);
        when(dishRepository.getPriceByDishes(any())).thenReturn(Double.valueOf(1));

        commandHandler = new OrderCommandHandler(repository, orderEventPublisher, dishRepository);
        repository.save(order);
    }
    @AfterEach
    void clearRepository(){
        repository.delete(order);
    }

    @DisplayName("Register new Order")
    @Test
    void registerOrder(){
        Order order1 = commandHandler.handle(new RegisterOrder(order.getCustomer(), order.getDishes()));
        assertEquals(2, order1.getDishes().size());
        assertEquals(OrderStatus.PENDING, order1.getOrderStatus());
        repository.delete(order1);
    }

    @DisplayName("Accept Order")
    @Test
    void acceptOrder(){
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(OrderStatus.ACCEPTED, commandHandler.handle(new AcceptOrder(order.getId())).getOrderStatus());
    }

    @DisplayName("Deny Order")
    @Test
    void denyOrder(){
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(OrderStatus.DENIED, commandHandler.handle(new DenyOrder(order.getId())).getOrderStatus());
    }

    @DisplayName("Cancel Order")
    @Test
    void cancelOrder(){
        commandHandler.handle(new AcceptOrder(order.getId()));
        assertEquals(OrderStatus.CANCELLED, commandHandler.handle(new CancelOrder(order.getId())).getOrderStatus());
    }

    @DisplayName("Order Ready To Delivered")
    @Test
    void orderReadyToDelivered(){
        commandHandler.handle(new AcceptOrder(order.getId()));
        assertEquals(OrderStatus.DELIVERING, commandHandler.handle(new DeliveringOrder(order.getId())).getOrderStatus());
    }

    @DisplayName("Order Delivered")
    @Test
    void deliverOrder(){
        commandHandler.handle(new AcceptOrder(order.getId()));
        commandHandler.handle(new DeliveringOrder(order.getId()));
        assertEquals(OrderStatus.DELIVERED, commandHandler.handle(new DeliveredOrder(order.getId())).getOrderStatus());
    }
}