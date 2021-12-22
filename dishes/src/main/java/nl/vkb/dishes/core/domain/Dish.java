package nl.vkb.dishes.core.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.vkb.dishes.core.domain.event.DishAdded;
import nl.vkb.dishes.core.domain.event.DishEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document("dishes")
public class Dish {
    @Id
    private UUID id;
    private String title;
    private double price;
    private List<Ingredient> ingredients;

    @Transient
    @JsonIgnore
    private List<DishEvent> events = new ArrayList<>();

    public Dish(String title, double price, List<Ingredient> ingredients) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.price = price;
        this.ingredients = ingredients;
        events.add(new DishAdded(this.id, this));
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(UUID id){
        ingredients.removeIf(ingredient -> ingredient.getId().equals(id));
    }

    public void clearEvents() {
        this.events.clear();
    }
}
