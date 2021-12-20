package nl.vkb.review.core.domain;

import lombok.Data;
import nl.vkb.review.core.domain.Exception.ReviewRatingException;
import nl.vkb.review.core.domain.event.ReviewEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
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
	@Transient
	private List<ReviewEvent> events = new ArrayList<>();

	public Review(String description, List<String> pros, List<String> cons, Rating rating, UUID orderId, UUID accountId){
		this.description = description;
		this.pros = pros;
		this.cons = cons;
		if (this.rating.getRatingNumber() > 0 || this.rating.getRatingNumber() < 5){
			this.rating = rating;
		}
		else throw new ReviewRatingException("rating number is not between 0 and 5");
		this.orderId = orderId;
		this.accountId = accountId;
	}

	public void clearEvents() {
		this.events.clear();
	}
}
