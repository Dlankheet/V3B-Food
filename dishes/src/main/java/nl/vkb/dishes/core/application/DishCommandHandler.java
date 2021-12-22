package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.command.createDish;
import nl.vkb.dishes.core.application.command.deleteDish;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.event.DishEvent;
import nl.vkb.dishes.core.port.messaging.DishEventPublisher;
import nl.vkb.dishes.core.port.storage.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCommandHandler {
    private final DishRepository dishRepo;
    private final StockRepository stockRepository;
    private final DishEventPublisher eventPublisher;

    public DishCommandHandler(DishRepository dishRepo, StockRepository stockRepository, DishEventPublisher eventPublisher) {
        this.dishRepo = dishRepo;
        this.stockRepository = stockRepository;
        this.eventPublisher = eventPublisher;
    }

    public Dish handle(createDish command) {
        Dish dish = new Dish(command.getTitle(), command.getPrice(), command.getIngredients());
        return this.publishEventsAndSave(dish);
    }

    public void handle(deleteDish command) {
        this.dishRepo.deleteById(command.getId().toString());
    }


    private Dish publishEventsAndSave(Dish dish) {
        List<DishEvent> events = dish.getEvents();
        events.forEach(eventPublisher::publish);
        dish.clearEvents();
        return this.dishRepo.save(dish);
    }
}
