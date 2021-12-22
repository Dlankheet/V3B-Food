package com.order.core.domain;

import com.order.core.domain.event.OrderAccepted;
import com.order.core.domain.event.OrderEvent;
import com.order.core.domain.exception.OrderStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
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
    @Disabled("accept order when the status PENDING")
    @Test
    void acceptOrderHappyFlow ( ) {
        order.acceptOrder();
        assertEquals(OrderStatus.ACCEPTED, order.getOrderStatus());
    }

    @Disabled("accept order when the order status not PENDING")
    @Test
    void acceptOrderFailFlow ( ) {
        order.acceptOrder();
        assertThrows(OrderStatusException.class, ()-> order.acceptOrder());
    }


    @Disabled("deny order when the order status PENDING")
    @Test
    void denyOrderHappyFlow ( ) {
        order.denyOrder();
        assertEquals(OrderStatus.DENIED, order.getOrderStatus());
    }

    @Disabled("deny order when the order status not PENDING")
    @Test
    void denyOrderFailFlow ( ) {
        order.acceptOrder();
        assertThrows(OrderStatusException.class, ()-> order.denyOrder());
    }

    @Disabled("cancel order when the order status ACCEPTED")
    @Test
    void cancelOrderHappyFlow ( ) {
        order.acceptOrder();
        order.cancelOrder();
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus());
    }

    @Disabled("cancel order when the order status not ACCEPTED")
    @Test
    void cancelOrderFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.cancelOrder());
    }

    @Disabled("get the order ready from the kitchen when the order status ACCEPTED")
    @Test
    void orderReadyToDeliveredHappyFlow ( ) {
        order.acceptOrder();
        order.orderReadyToDelivered();
        assertEquals(OrderStatus.DELIVERING, order.getOrderStatus());
    }

    @Disabled("get the order ready from the kitchen when the order status not ACCEPTED")
    @Test
    void orderReadyToDeliveredFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.orderReadyToDelivered());
    }

    @Disabled("Deliver order when the order status DELIVERING")
    @Test
    void orderDeliveredHappyFlow ( ) {
        order.setOrderStatus(OrderStatus.DELIVERING);
        order.orderDelivered();
        assertEquals(OrderStatus.DELIVERED, order.getOrderStatus());
    }

    @Disabled("Deliver order when the order status not DELIVERING")
    @Test
    void orderDeliveredFailFlow ( ) {
        assertThrows(OrderStatusException.class, ()-> order.orderDelivered());
    }

    @Disabled("clear events list for the order")
    @Test
    void clearEvents ( ) {
        order.acceptOrder();
        order.orderReadyToDelivered();
        assertNotEquals(0, order.listEvents().size());
        order.clearEvents();
        assertEquals(0, order.listEvents().size());
    }

    @Disabled("")
    @Test
    void listEvents ( ) {
        order.acceptOrder();
        assertEquals(1, order.listEvents().size());
    }
}