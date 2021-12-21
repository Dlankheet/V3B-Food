package nl.vkb.review.core.domain;

import lombok.Data;
import nl.vkb.review.core.domain.Exception.ReviewRatingException;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Rating {
	@Id
	private UUID id;
	private double ratingNumber;

	public Rating(double ratingNumber) {
		if (this.ratingNumber > 0 && this.ratingNumber <= 5) this.ratingNumber = ratingNumber;
		else throw new ReviewRatingException("Could not be created");
	}
}
