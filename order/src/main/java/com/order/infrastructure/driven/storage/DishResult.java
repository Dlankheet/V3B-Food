package com.order.infrastructure.driven.storage;

import lombok.Data;

import java.util.List;


public class DishResult {
    public boolean available;
    public List<String> unavailableDishes;
    public double price;
}
