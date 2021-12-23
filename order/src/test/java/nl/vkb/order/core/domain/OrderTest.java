package nl.vkb.order.core.domain;

import nl.vkb.order.core.domain.exception.OrderStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;

    @BeforeEach
    void setup(){
        Set<UUID> dishes = new HashSet<>();
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52be"));
        dishes.add(UUID.fromString("2ead96d4-d2d1-2040-8f48-ed3e7d6c52ba"));
        order = new Order(UUID.randomUUID(), dishes);

    }
    @DisplayName("accept order when the status PENDING")
    @Test
    void acceptOrderHappyFlow ( ) {
        order.acceptOrder();
        assertEquals(OrderStatus.ACCEPTED, order.getOrderStatus());
    }

    @DisplayName("accept order when the order status not PENDING")
    @Test
    void acceptOrderFailFlow ( ) {
        order.acceptOrder();
        assertThrows(OrderStatusException.class, ()-> order.acceptOrder());
    }


    @DisplayName("deny order when the order status PENDING")
    @Test
    void denyOrderHappyFlow ( ) {
        order.denyOrder();
        assertEquals(OrderStatus.DENIED, order.getOrderStatus());
    }

    @DisplayName("deny order when the order status not PENDING")
    @Test
    void denyOrderFailFlow ( ) {
        order.acceptOrder();
        assertThrows(OrderStatusException.class, ()-> order.denyOrder());
    }

    @DisplayName("cancel order when the order status ACCEPTED")
    @Test
    void cancelOrderHappyFlow ( ) {
        order.acceptOrder();
        order.cancelOrder();
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
    }

    @DisplayName("cancel order when the order status not ACCEPTED")
    @Test
    void cancelOrderFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.cancelOrder());
    }

    @DisplayName("get the order ready from the kitchen when the order status ACCEPTED")
    @Test
    void orderReadyToDeliveredHappyFlow ( ) {
        order.acceptOrder();
        order.orderReadyToDelivered();
        assertEquals(OrderStatus.DELIVERING, order.getOrderStatus());
    }

    @DisplayName("get the order ready from the kitchen when the order status not ACCEPTED")
    @Test
    void orderReadyToDeliveredFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.orderReadyToDelivered());
    }

    @DisplayName("Deliver order when the order status DELIVERING")
    @Test
    void orderDeliveredHappyFlow ( ) {
        order.setOrderStatus(OrderStatus.DELIVERING);
        order.orderDelivered();
        assertEquals(OrderStatus.DELIVERED, order.getOrderStatus());
    }

    @DisplayName("Deliver order when the order status not DELIVERING")
    @Test
    void orderDeliveredFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.orderDelivered());
    }

    @DisplayName("clear events list for the order")
    @Test
    void clearEvents ( ) {
        order.acceptOrder();
        order.orderReadyToDelivered();
        assertNotEquals(0, order.listEvents().size());
        order.clearEvents();
        assertEquals(0, order.listEvents().size());
    }

    @DisplayName("add event to event list")
    @Test
    void listEvents ( ) {
        assertEquals(1, order.listEvents().size());
    }
}