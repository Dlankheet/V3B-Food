package nl.vkb.order.core.domain.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String id){
        super(String.format("order with id :  %s not found ", id));
    }
}
