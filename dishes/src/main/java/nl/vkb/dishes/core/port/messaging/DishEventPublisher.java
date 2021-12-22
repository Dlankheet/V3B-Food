package nl.vkb.dishes.core.port.messaging;

import nl.vkb.dishes.core.domain.event.DishEvent;

public interface DishEventPublisher {
    void publish(DishEvent event);
}
