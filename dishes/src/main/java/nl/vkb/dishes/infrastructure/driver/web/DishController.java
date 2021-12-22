package nl.vkb.dishes.infrastructure.driver.web;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.service.DishService;
import nl.vkb.dishes.infrastructure.driver.web.DTO.dishDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dish")
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Dish> getDishes(){
        return service.getDishes();
    }

    @PostMapping("/create")
    public String createDish(@RequestBody dishDTO dto){
        Dish generatedDish = service.createDish(dto.title, dto.price, dto.ingredients);
        return "Generated Succesfully: " + generatedDish;
    }

    @DeleteMapping("/remove")
    public String generateRandomDish(String id){
        Dish removedDish = service.removeDish(id);
        return "Deleted Succesfully: " + removedDish;
    }

    @GetMapping("/{id}/isAvailable")
    public Boolean isDishAvailable(@PathVariable String id){
        return service.isDishAvailable(id);
    }
}