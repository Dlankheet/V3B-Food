package nl.hu.v3bfood_dishes.core.port.messaging;

import nl.hu.v3bfood_dishes.core.domain.event.DishEvent;

public interface DishEventPublisher {
    void publish(DishEvent event);
}
