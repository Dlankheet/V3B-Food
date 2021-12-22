package nl.vkb.dishes.core.service;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.HttpStockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepo;
    private final StockRepository stockRepository;

    public DishService(DishRepository dishRepo, StockRepository stockRepository) {
        this.dishRepo = dishRepo;
        this.stockRepository = stockRepository;
    }

    public List<Dish> getDishes(){
        return dishRepo.findAll();
    }

    public Dish createDish(String title, double price, List<Ingredient> ingredients) {
        Dish dish = new Dish(title, price, ingredients);
        return dishRepo.save(dish);
    }

    public Dish removeDish(String id){
        Optional<Dish> optionalDish = dishRepo.findById(id);
        Dish dish = optionalDish.orElseThrow();
        dishRepo.deleteById(id);
        return dish;
    }

    public Boolean isDishAvailable(String id) {
        Optional<Dish> optionalDish = dishRepo.findById(id);
        Dish dish = optionalDish.orElseThrow();
        for(Ingredient ingredient : dish.getIngredients()){
            StockResult stockIngredient = stockRepository.findIngredientById(ingredient.getId());
            if(stockIngredient.stock < ingredient.getAmount()){
                return false;
            }
        }
        return true;
    }
}
