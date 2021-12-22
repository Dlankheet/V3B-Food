package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.query.CheckOrderAvailability;
import nl.vkb.dishes.core.application.query.ListDishes;
import nl.vkb.dishes.core.application.query.CheckAvailable;
import nl.vkb.dishes.core.application.query.ListDishesById;
import nl.vkb.dishes.core.application.results.OrderAvailableResult;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Exception.DishNotFoundException;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DishQueryHandler {
    private final DishRepository dishRepository;
    private final StockRepository stockRepository;

    public DishQueryHandler(DishRepository dishRepository, StockRepository stockRepository) {
        this.dishRepository = dishRepository;
        this.stockRepository = stockRepository;
    }

    public Boolean handle(CheckAvailable query){
        Optional<Dish> optionalDish = dishRepository.findById(query.getId());
        Dish dish = optionalDish.orElseThrow(() -> new DishNotFoundException("This dish is not available"));
        for (Ingredient ingredient : dish.getIngredients()) {
            StockResult stockIngredient = stockRepository.findIngredientById(ingredient.getId());
            if (stockIngredient.getStock() < ingredient.getAmount()) {
                return false;
            }
        }
        return true;
    }

    public List<Dish> handle (ListDishesById query){
        List<Dish> dishes = new ArrayList<>();
        this.dishRepository.findAllById(query.getDishIds()).forEach(dishes::add);
        return dishes;
    }
    public List<Dish> handle(ListDishes query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.dishRepository.findAll(sort);
    }

    public OrderAvailableResult handle(CheckOrderAvailability query){
        Boolean allAvailable = true;
        Double totalPrice = 0.0;

        List<Dish> dishes = handle(new ListDishesById(query.getDishIds()));
        List<UUID> unavailableDishes = new ArrayList<>();

        for(Dish dish : dishes){
            if(!handle(new CheckAvailable(dish.getId()))){
                unavailableDishes.add(dish.getId());
                allAvailable = false;
            }else{
                totalPrice += dish.getPrice();
               }
            }
        return new OrderAvailableResult(allAvailable, unavailableDishes, totalPrice);
        }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);
        if (direction.equals("desc")) {
            sort = sort.descending();
        }
        return sort;
    }
}
