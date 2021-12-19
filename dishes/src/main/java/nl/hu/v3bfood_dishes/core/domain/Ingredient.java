package nl.hu.v3bfood_dishes.core.domain;

import java.util.List;

public class Ingredient {
    private int amount;
    private String name;
    private List<Allergy> allergies;

    public Ingredient(int amount, String name, List<Allergy> allergies) {
        this.amount = amount;
        this.name = name;
        this.allergies = allergies;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }
}
