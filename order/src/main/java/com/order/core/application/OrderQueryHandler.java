package com.order.core.application;

import com.order.core.application.query.FindAllOrderByCustomerId;
import com.order.core.application.query.GetOrderById;
import com.order.core.domain.Order;
import com.order.core.domain.exception.OrderNotFoundException;
import com.order.core.port.data.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler {
    private final OrderRepository repository;

    public OrderQueryHandler (OrderRepository repository) {
        this.repository = repository;
    }
    public Order handle(GetOrderById query){
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new OrderNotFoundException(query.getId().toString()));
    }

    public List<Order> handle(FindAllOrderByCustomerId query){
        return this.repository.findAllByCustomer(query.getCustomer());
    }
    public List<Order> handle(){
        return this.repository.findAll();
    }
}
