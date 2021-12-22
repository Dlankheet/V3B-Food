package nl.vkb.review.core.domain.Service;

import nl.vkb.review.core.Service.Query.GetReviewById;
import nl.vkb.review.core.Service.ReviewQueryService;
import nl.vkb.review.core.domain.Exception.ReviewNotFoundException;
import nl.vkb.review.core.domain.Rating;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.port.storage.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
public class ReviewQueryServiceTest {

	@Autowired
	private ReviewRepository repository;
	@Autowired
	private ReviewQueryService service;
	private Review review;
	private Review savedReview;

	@BeforeEach
	void saveReview() {
		this.review=repository.save(new Review("desc", new ArrayList<>(), new ArrayList<>(),
				new Rating(2.5), UUID.randomUUID(), UUID.randomUUID()));

		this.savedReview = repository.findById(review.getId()).get();
	}
	@AfterEach
	void clearRepository(){
		repository.deleteAll();
	}

	@Test
	void getReviewTest() {
		Assertions.assertEquals("desc", savedReview.getDescription());
		Assertions.assertTrue(savedReview.getPros().isEmpty());
		Assertions.assertTrue(savedReview.getPros().isEmpty());
		Assertions.assertEquals(2.5, savedReview.getRatingNumber());
	}

	@Test
	void getReviewFailTest() {
		Assertions.assertThrows(ReviewNotFoundException.class,
				() -> service.handle(new GetReviewById(UUID.randomUUID()))
		);
	}
}
