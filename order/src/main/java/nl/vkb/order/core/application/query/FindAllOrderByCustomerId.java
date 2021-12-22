package nl.vkb.order.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FindAllOrderByCustomerId {
    private final UUID customer;

}
