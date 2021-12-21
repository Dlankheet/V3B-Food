package nl.hu.v3bfood_dishes.core.service;
import nl.hu.v3bfood_dishes.core.domain.Dish;
import nl.hu.v3bfood_dishes.core.domain.DishRepository;
import nl.hu.v3bfood_dishes.core.domain.Ingredient;
import nl.hu.v3bfood_dishes.infrastructure.driven.storage.HttpStockRepository;
import nl.hu.v3bfood_dishes.infrastructure.driven.storage.StockResult;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepo;
    private final HttpStockRepository stockRepository;

    public DishService(DishRepository dishRepo, HttpStockRepository stockRepository) {
        this.dishRepo = dishRepo;
        this.stockRepository = stockRepository;
    }

    public List<Dish> getDishes(){
        return dishRepo.findAll();
    }

    public Dish createDish(String title, double price, List<Ingredient> ingredients) {
        Dish dish = new Dish(title, price, ingredients);
        Dish saveddish = dishRepo.save(dish);
        return saveddish;
    }

    public Dish removeDish(String id){
        Optional<Dish> optionalDish = dishRepo.findById(id);
        Dish dish = optionalDish.orElseThrow();
        dishRepo.deleteById(id);
        return dish;
    }
//    public List<Allergy> getAllergiesByDish(String id){
//        Optional<Dish> optionalDish = dishRepo.findById(id);
//        Dish dish = optionalDish.orElseThrow();
//        return dish.getAllergies();
//    }

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
