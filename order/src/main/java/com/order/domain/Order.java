package com.order.domain;


import org.springframework.data.annotation.Id;

public class Order {
    @Id
    private int id;
    private boolean paid;
    private OrderStatus orderStatus;
    private Customer customer;
    private Dish dish;
}
