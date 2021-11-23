package nl.vkb.ingredients;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ingredients")
public class Ingredient {
	@Id
	public String id;
	public String name;
}
