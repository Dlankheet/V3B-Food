package nl.vkb.dishes.core.domain;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document("dishes")
public class Dish {
    @Id
    private String id;
    private String title;
    private double price;
    private List<Ingredient> ingredients;

    public Dish(String title, double price, List<Ingredient> ingredients) {
        this.title = title;
        this.price = price;
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(UUID id){
        ingredients.removeIf(ingredient -> ingredient.getId().equals(id));
    }

}
