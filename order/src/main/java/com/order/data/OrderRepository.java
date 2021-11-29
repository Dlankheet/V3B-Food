package com.order.data;

import com.order.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order getOrderById(String id);
}