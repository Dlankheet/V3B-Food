package nl.hu.v3bfood_dishes.core.port;

import nl.hu.v3bfood_dishes.core.domain.Dish;
import nl.hu.v3bfood_dishes.core.service.DishService;
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
    public String createDish(String title, double price){
        Dish generatedDish = service.createDish(title, price);
        return "Generated Succesfully: " + generatedDish;
    }

    @DeleteMapping("/remove")
    public String generateRandomDish(String id){
        Dish removedDish = service.removeDish(id);
        return "Deleted Succesfully: " + removedDish;
    }
}
