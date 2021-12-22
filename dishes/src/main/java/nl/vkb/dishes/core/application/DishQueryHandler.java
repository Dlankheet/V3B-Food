package nl.vkb.dishes.core.application;

import nl.vkb.dishes.core.application.query.ListDishes;
import nl.vkb.dishes.core.application.query.CheckAvailable;
import nl.vkb.dishes.core.domain.Dish;
import nl.vkb.dishes.core.domain.DishRepository;
import nl.vkb.dishes.core.domain.Ingredient;
import nl.vkb.dishes.core.port.storage.StockRepository;
import nl.vkb.dishes.infrastructure.driven.storage.StockResult;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        Dish dish = optionalDish.orElseThrow();
        for (Ingredient ingredient : dish.getIngredients()) {
            StockResult stockIngredient = stockRepository.findIngredientById(ingredient.getId());
            if (stockIngredient.getStock() < ingredient.getAmount()) {
                return false;
            }
        }
        return true;
    }

    public List<Dish> handle(ListDishes query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.dishRepository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);
        if (direction.equals("desc")) {
            sort = sort.descending();
        }
        return sort;
    }
}
