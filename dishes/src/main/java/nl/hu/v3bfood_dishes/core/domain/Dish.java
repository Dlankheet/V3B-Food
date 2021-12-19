package nl.hu.v3bfood_dishes.core.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("dishes")
public class Dish {
    @Id
    private String id;
    private String title;
    private double price;
    private int coockingTime;
    private List<Ingredient> ingredients;

    public Dish(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Allergy> getAllergies(){
        List<Allergy> allergies = new ArrayList<>();
        for(Ingredient ingredient: ingredients){
            allergies.addAll(ingredient.getAllergies());
        }
        return allergies;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
