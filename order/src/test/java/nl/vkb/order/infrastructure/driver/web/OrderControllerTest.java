package nl.vkb.order.infrastructure.driver.web;

import nl.vkb.order.core.application.OrderCommandHandler;
import nl.vkb.order.core.application.OrderQueryHandler;
import nl.vkb.order.core.application.command.AcceptOrder;
import nl.vkb.order.core.application.command.RegisterOrder;
import nl.vkb.order.core.application.query.FindAllOrderByCustomerId;
import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.OrderStatus;
import nl.vkb.order.core.domain.event.OrderEvent;
import nl.vkb.order.core.port.data.DishRepository;
import nl.vkb.order.core.port.data.OrderRepository;
import nl.vkb.order.core.port.messaging.OrderEventPublisher;
import nl.vkb.order.infrastructure.driver.web.request.RegisterOrderRequest;
import nl.vkb.order.infrastructure.driver.web.response.OrderDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@SpringBootTest
class OrderControllerTest {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderQueryHandler queryHandler;
    private OrderController controller;
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

        OrderCommandHandler commandHandler = new OrderCommandHandler(repository, orderEventPublisher, dishRepository);
        controller = new OrderController(commandHandler, queryHandler);
        repository.save(order);
    }
    @AfterEach
    void clearRepository(){
        repository.delete(order);
    }
    @Test
    void registerOrder ( ) {
        OrderDto order1 = controller.registerOrder(new RegisterOrderRequest(order.getCustomer(), List.copyOf(order.getDishes())));
        assertEquals(2, order1.getDishes().size());
        assertEquals(OrderStatus.PENDING, order1.getStatus());
    }

    @DisplayName("Get order based on his id ")
    @Test
    void getOrderById ( ) {
        assertEquals(order.getOrderStatus(), controller.getOrderById(order.getId()).getStatus());
        assertEquals(order.getId(), controller.getOrderById(order.getId()).getId());
    }

    @DisplayName("Get orders based on customer id")
    @Test
    void getAllByCustomer ( ) {
        assertEquals(1, controller.getAllByCustomer(order.getCustomer().toString()).size());
        assertEquals(order.getId(), controller.getAllByCustomer(order.getCustomer().toString()).get(0).getId());
    }
    @DisplayName("Accept Order")
    @Test
    void acceptOrder ( ) {
        assertEquals(OrderStatus.ACCEPTED, controller.acceptOrder(order.getId()).getStatus());
    }

    @DisplayName("Cancel Order")
    @Test
    void cancelOrder ( ) {
        controller.acceptOrder(order.getId());
        assertEquals(OrderStatus.CANCELLED, controller.cancelOrder(order.getId()).getStatus());
    }

    @DisplayName("Deny Order")
    @Test
    void denyOrder ( ) {
        assertEquals(OrderStatus.DENIED, controller.denyOrder(order.getId()).getStatus());
    }

    @DisplayName("Order Ready To Delivered")
    @Test
    void deliveringOrder ( ) {
        controller.acceptOrder(order.getId());
        assertEquals(OrderStatus.DELIVERING, controller.deliveringOrder(order.getId()).getStatus());
    }
}