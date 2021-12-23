package nl.vkb.order.core.application;

import nl.vkb.order.core.application.query.FindAllOrderByCustomerId;
import nl.vkb.order.core.application.query.GetOrderById;
import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.exception.OrderNotFoundException;
import nl.vkb.order.core.port.data.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderQueryHandlerTest {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderQueryHandler queryHandler;
    private Order order;

    @BeforeEach
    void setup(){
        Set<UUID> dishes = new HashSet<>();
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52be"));
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52ba"));
        order = new Order(UUID.randomUUID(), dishes);
        repository.save(order);
    }
    @AfterEach
    void clearRepository(){
        repository.delete(order);
    }

    @DisplayName("Get order based on his id ")
    @Test
    void getOrderByIdHappyFlow ( ) {
        assertEquals(order.getId(), queryHandler.handle(new GetOrderById(order.getId())).getId());
        assertEquals(order.getDishes(), queryHandler.handle(new GetOrderById(order.getId())).getDishes());
    }

    @DisplayName("Get order based on his id when not found")
    @Test
    void getOrderByIdFailFlow ( ) {
        repository.delete(order);
        assertThrows(OrderNotFoundException.class, ()-> {queryHandler.handle(new GetOrderById(order.getId()));});
    }

    @DisplayName("Get order based on customer id")
    @Test
    void findAllOrderByCustomerId ( ) {
        assertEquals(1, queryHandler.handle(new FindAllOrderByCustomerId(order.getCustomer())).size());
        assertEquals(order.getId(), queryHandler.handle(new FindAllOrderByCustomerId(order.getCustomer())).get(0).getId());

    }
    @DisplayName("Get all order")
    @Test
    void getAllOrders ( ) {
        assertTrue( queryHandler.handle().size() > 0);
    }
}