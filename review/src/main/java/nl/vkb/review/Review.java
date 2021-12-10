package nl.vkb.review;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;
@Data
public class Review {
	@Id
	private UUID id;
	private String description;
	private List<String> pros;
	private List<String> cons;
	private Rating rating;

	public Review(String description, List<String> pros, List<String> cons, Rating rating){
		this.description = description;
		this.pros = pros;
		this.cons = cons;
		if (this.rating.getRating() > 0 || this.rating.getRating() < 5){
			this.rating = rating;
		}
	}
}
