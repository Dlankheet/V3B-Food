package nl.vkb.order.infrastructure.driven.exception;

import java.util.List;

public class DishUnavailableException extends RuntimeException{
    public DishUnavailableException(List<String> dishes){
        super(String.format("Dishes :  %s unavailable.",dishes.toString()));
    }
}
