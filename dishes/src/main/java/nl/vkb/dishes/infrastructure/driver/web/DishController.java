package nl.vkb.dishes.infrastructure.driver.web;

import nl.vkb.dishes.core.application.DishCommandHandler;
import nl.vkb.dishes.core.application.DishQueryHandler;
import nl.vkb.dishes.core.application.command.createDish;
import nl.vkb.dishes.core.application.command.deleteDish;
import nl.vkb.dishes.core.application.query.ListDishes;
import nl.vkb.dishes.core.application.query.checkAvailable;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.infrastructure.driver.web.request.createDishRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("dish")
public class DishController {
    private final DishCommandHandler commandHandler;
    private final DishQueryHandler queryHandler;

    public DishController(DishCommandHandler commandHandler, DishQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/create")
    public Dish createDish(@RequestBody createDishRequest dto) {
        return this.commandHandler.handle(new createDish(dto.getTitle(), dto.getPrice(), dto.getIngredients()));
    }

    @GetMapping("/all")
    public List<Dish> getDishes(@RequestParam(required = false) String orderBy,
                                @RequestParam(required = false) String direction) {
        return this.queryHandler.handle(new ListDishes(orderBy, direction));
    }

    @DeleteMapping("/remove")
    public void generateRandomDish(String id) {
        commandHandler.handle(new deleteDish(UUID.fromString(id)));
    }

    @GetMapping("/{id}/isAvailable")
    public Boolean isDishAvailable(@PathVariable String id) {
        return queryHandler.handle(new checkAvailable(id));
    }

}
