package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.query.*;
import nl.vkb.dishes.core.application.results.OrderAvailableResult;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Exception.DishNotFoundException;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DishQueryHandler {
    private final DishRepository dishRepository;
    private final StockRepository stockRepository;

    public DishQueryHandler(DishRepository dishRepository, StockRepository stockRepository) {
        this.dishRepository = dishRepository;
        this.stockRepository = stockRepository;
    }

    public Boolean handle(CheckAvailable query) {
        Optional<Dish> optionalDish = dishRepository.findById(query.getId());
        Dish dish = optionalDish.orElseThrow(() -> new DishNotFoundException("This dish is not available"));
        System.out.println(dish.getIngredients());
        return handle(new CheckStock(dish.getIngredients()));
    }

    public Boolean handle(CheckStock query) {
        List<Ingredient> neededIngredients = query.getIngredients();
        ArrayList<UUID> ids = new ArrayList<>();

        neededIngredients.forEach(ingredient -> ids.add(ingredient.getId()));
        List<StockResult> stockIngredients = stockRepository.findIngredientByIds(ids);

        for (StockResult stockIngredient : stockIngredients) {
            System.out.println(stockIngredient);
            for (Ingredient ingredient : neededIngredients) {
                System.out.println(ingredient);
                if(ingredient.getId() == stockIngredient.getId()){
                    System.out.println("Stock: " + stockIngredient + " Needed: " + ingredient);
                    if(stockIngredient.getStock() < ingredient.getAmount()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Dish> handle(ListDishesById query) {
        List<Dish> dishes = new ArrayList<>();
        for(UUID id : query.getDishIds()){
            dishes.add(this.dishRepository.findById(id).orElseThrow(() -> new DishNotFoundException("Dish not found.")));
        }
        return dishes;
    }

    public List<Dish> handle(ListDishes query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.dishRepository.findAll(sort);
    }

    //Following function checks if each individual dish is available,
    //but also calculates totalprice if ingredients aren't available.
    //It also returns allAvailable false if the ingredients of the full order aren't present.
    public OrderAvailableResult handle(CheckOrderAvailability query) {
        boolean allAvailable = true;
        double totalPrice = 0.0;

        List<Dish> dishes = handle(new ListDishesById(query.getDishIds()));
        List<UUID> unavailableDishes = new ArrayList<>();

        HashMap<UUID, Integer> totalIngredientsMap = new HashMap<UUID, Integer>();
        ArrayList<Ingredient> totalIngredientsList = new ArrayList<>();

        for (Dish dish : dishes) {
            for (Ingredient ingredient : dish.getIngredients()) {
                totalIngredientsMap.putIfAbsent(ingredient.getId(), ingredient.getAmount());
                totalIngredientsMap.computeIfPresent(ingredient.getId(), (k, v) -> v + ingredient.getAmount());
            }
            if (!handle(new CheckAvailable(dish.getId()))) {
                unavailableDishes.add(dish.getId());
                allAvailable = false;
            } else {
                totalPrice += dish.getPrice();
            }
        }

        for(Map.Entry<UUID, Integer> entry : totalIngredientsMap.entrySet()){
            totalIngredientsList.add(new Ingredient(entry.getKey(),entry.getValue()));
        }

        if(allAvailable && !handle(new CheckStock(totalIngredientsList))){
            allAvailable = false;
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
