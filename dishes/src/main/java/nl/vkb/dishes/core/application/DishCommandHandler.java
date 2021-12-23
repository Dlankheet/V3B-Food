package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.command.AddIngredient;
import nl.vkb.dishes.core.application.command.CreateDish;
import nl.vkb.dishes.core.application.command.DeleteDish;
import nl.vkb.dishes.core.application.command.RemoveIngredient;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Exception.DishNotFoundException;
import nl.vkb.dishes.core.domain.event.DishEvent;
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

    public Dish handle (AddIngredient command){
        Dish dish = dishRepo.findById(command.getDishId()).orElseThrow(()-> new DishNotFoundException("Dish does not exist."));
        dish.addIngredient(command.getIngredient());
        return this.publishEventsAndSave(dish);
    }

    public Dish handle (RemoveIngredient command){
        Dish dish = dishRepo.findById(command.getDishId()).orElseThrow(()-> new DishNotFoundException("Dish does not exist."));
        dish.removeIngredient(command.getIngredientID());
        return this.publishEventsAndSave(dish);
    }

    private Dish publishEventsAndSave(Dish dish) {
        List<DishEvent> events = dish.getEvents();
        events.forEach(eventPublisher::publish);
        dish.clearEvents();
        return this.dishRepo.save(dish);
    }
}
