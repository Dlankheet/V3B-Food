package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.command.CreateDish;
import nl.vkb.dishes.core.application.command.DeleteDish;
import nl.vkb.dishes.core.application.command.DishOrdered;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.event.DishEvent;
import nl.vkb.dishes.core.domain.exceptions.DishNotFoundException;
import nl.vkb.dishes.core.port.messaging.DishEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCommandHandler {
    private final DishRepository dishRepo;
    private final DishEventPublisher eventPublisher;

    public DishCommandHandler(DishRepository dishRepo, DishEventPublisher eventPublisher) {
        this.dishRepo = dishRepo;
        this.eventPublisher = eventPublisher;
    }

    public Dish handle(CreateDish command) {
        Dish dish = new Dish(command.getTitle(), command.getPrice(), command.getIngredients());
        return this.publishEventsAndSave(dish);
    }

    public void handle(DeleteDish command) {
        this.dishRepo.deleteById(command.getId());
    }

    public void handle(DishOrdered command) {
        Dish dish=dishRepo.findById(command.getDish()).orElseThrow(()->new DishNotFoundException(command.getDish().toString()));
        dish.order();
        publishEventsAndSave(dish);
    }

    private Dish publishEventsAndSave(Dish dish) {
        List<DishEvent> events = dish.getEvents();
        events.forEach(eventPublisher::publish);
        dish.clearEvents();
        return this.dishRepo.save(dish);
    }
}
