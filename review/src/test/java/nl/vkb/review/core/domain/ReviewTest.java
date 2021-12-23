package nl.vkb.review.core.domain;

import nl.vkb.review.core.domain.exception.ReviewRatingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {
	private Review review;
	private Rating rating;
	private List<String> pros = new ArrayList<>();
	private List<String> cons = new ArrayList<>();

	@BeforeEach
	void init() {
		pros.add("Good");
		cons.add("Bad");
		rating = new Rating(3.4);
		this.review = new Review("test", pros, cons, rating,
				UUID.randomUUID(), UUID.randomUUID());
	}

	@Test
	void createReviewTest() {
		assertNotNull(review.getId());
	}

	@Test
	void changeReviewRatingTest() {
		this.review.getRating().changeRating(4.6);
		assertEquals(4.6, this.review.getRating().getRatingNumber());
	}

	@Test
	void changeReviewRatingFailTest() {
		Rating rating = this.review.getRating();
		assertThrows(ReviewRatingException.class,
				() -> rating.changeRating(5.1)
		);
	}

	@Test
	void changeReviewRatingFailTest2() {
		Rating rating = this.review.getRating();
		assertThrows(ReviewRatingException.class,
				() -> rating.changeRating(0)
		);
	}

	@Test
	void RatingNotValidTest() {
		assertThrows(ReviewRatingException.class,
				() -> new Rating(5.1)
		);
	}
}

