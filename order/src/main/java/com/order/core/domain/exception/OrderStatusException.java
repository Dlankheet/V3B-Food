package com.order.core.domain.exception;

public class OrderStatusException extends RuntimeException{
    public OrderStatusException(String start, String end){
        super(String.format("cannot switch directly from %s to %s ", start, end));
    }
}
