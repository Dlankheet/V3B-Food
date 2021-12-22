package nl.vkb.review.core.domain;

import lombok.Getter;
import nl.vkb.review.core.domain.Exception.ReviewRatingException;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
public class Rating {
	private double ratingNumber;

	public Rating(double ratingNumber) {
		if (ratingNumber > 0 && ratingNumber <= 5) this.ratingNumber = ratingNumber;
		else throw new ReviewRatingException("Could not be created");
	}

	public void changeRating(double rNumber) {
		this.ratingNumber = rNumber;
	}

}
