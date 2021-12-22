package nl.vkb.dishes.core.domain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DishRepository extends MongoRepository<Dish, UUID>{
}
