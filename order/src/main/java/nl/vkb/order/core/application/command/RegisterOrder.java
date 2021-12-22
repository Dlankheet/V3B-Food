package nl.vkb.order.core.application.command;

import lombok.Getter;

import java.util.Set;

@Getter
public class RegisterOrder {
    private final String customer;
    private final Set<String > dishes;


    public RegisterOrder (String customer, Set<String> dishes) {
        this.customer = customer;
        this.dishes = dishes;
    }
}
