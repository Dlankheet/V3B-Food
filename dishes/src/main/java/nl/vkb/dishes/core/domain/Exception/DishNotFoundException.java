package nl.vkb.dishes.core.domain.Exception;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);
    }
}
