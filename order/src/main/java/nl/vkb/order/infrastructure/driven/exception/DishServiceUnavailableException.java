package nl.vkb.order.infrastructure.driven.exception;

public class DishServiceUnavailableException extends RuntimeException{
    public DishServiceUnavailableException(){
        super("service unavailable, try it again");
    }
}
