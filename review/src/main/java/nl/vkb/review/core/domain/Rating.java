package nl.vkb.review.core.domain;

import lombok.Getter;
import nl.vkb.review.core.domain.exception.ReviewRatingException;

@Getter
public class Rating {
	private double ratingNumber;

	public Rating(double ratingNumber) {
		this.ratingCheck(ratingNumber);
	}

	public void changeRating(double rNumber) {
		ratingCheck(rNumber);
	}

	private void ratingCheck(double ratingNumber) {
		if (ratingNumber > 0 && ratingNumber <= 5) this.ratingNumber = ratingNumber;
		else throw new ReviewRatingException("Number not within 0 or 5");
	}

}
