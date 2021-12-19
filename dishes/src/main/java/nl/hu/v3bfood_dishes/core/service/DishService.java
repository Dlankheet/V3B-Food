package nl.hu.v3bfood_dishes.core.service;
import nl.hu.v3bfood_dishes.core.domain.Allergy;
import nl.hu.v3bfood_dishes.core.domain.Dish;
import nl.hu.v3bfood_dishes.core.domain.DishRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepo;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public DishService(DishRepository dishRepo) {
        this.dishRepo = dishRepo;
    }

    public List<Dish> getDishes(){
        return dishRepo.findAll();
    }

    public Dish createDish(String title, double price) {
        Dish dish = new Dish(title, price);
        Dish saveddish = dishRepo.save(dish);
        rabbitTemplate.convertAndSend(this.queue.getName(), "Generated Dish = " + saveddish);
        return saveddish;
    }

    public Dish removeDish(String id){
        Optional<Dish> optionalDish = dishRepo.findById(id);
        Dish dish = optionalDish.orElseThrow();
        dishRepo.deleteById(id);
        rabbitTemplate.convertAndSend(this.queue.getName(), "Removed Dish = " + dish);
        return dish;
    }

    public List<Allergy> getAllergiesByDish(String id){
        Optional<Dish> optionalDish = dishRepo.findById(id);
        Dish dish = optionalDish.orElseThrow();
        return dish.getAllergies();
    }
}
