package nl.vkb.ingredients.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import nl.vkb.ingredients.core.domain.event.StockDeleted;
import nl.vkb.ingredients.core.domain.event.StockEvent;
import nl.vkb.ingredients.core.domain.event.StockUpdated;
import nl.vkb.ingredients.core.domain.exception.InvalidStockException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "ingredients")
@Getter
@EqualsAndHashCode
public class Ingredient {
	@Id
	private UUID id;
	private String name;
	private int stock;

	@Transient
	private List<StockEvent> events = new ArrayList<>();

	public Ingredient(String name, int stock) {
		this.id=UUID.randomUUID();
		this.name=name;
		this.stock=stock;
	}
	public void addStock(int stock) {
		if((this.stock+stock)<0) {
			throw new InvalidStockException("Stock is too low!");
		}
		this.stock+=stock;
		this.events.add(new StockUpdated(id,this.stock));
	}
	public void setStock(int stock) {
		this.stock = stock;
		this.events.add(new StockUpdated(id,stock));
	}
	public void delete() {
		this.events.add(new StockDeleted(id));
	}
	public void clearEvents() {
		this.events.clear();
	}
}
