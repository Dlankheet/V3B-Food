package nl.vkb.review.domain;

import lombok.Data;
import nl.vkb.review.Exception.ReviewRatingException;
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
	private UUID orderId;
	private UUID accountId;

	public Review(String description, List<String> pros, List<String> cons, Rating rating, UUID orderId, UUID accountId){
		this.description = description;
		this.pros = pros;
		this.cons = cons;
		if (this.rating.getRating() > 0 || this.rating.getRating() < 5){
			this.rating = rating;
		}
		else throw new ReviewRatingException("rating number is not between 0 and 5");
		this.orderId = orderId;
		this.accountId = accountId;
	}
}
