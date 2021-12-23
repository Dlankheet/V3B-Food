package nl.vkb.review.core.domain.Service;

import nl.vkb.review.core.Service.Command.ChangeRating;
import nl.vkb.review.core.Service.Command.DeleteReview;
import nl.vkb.review.core.Service.Command.MakeReview;
import nl.vkb.review.core.Service.ReviewCommandService;
import nl.vkb.review.core.domain.Rating;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.port.storage.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewCommandServiceTest {

		@Autowired
		private ReviewRepository repository;
		@Autowired
		private ReviewCommandService service;
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
		void createReviewTest() {
			Review cReview = service.handle(new MakeReview("desc", new ArrayList<>(), new ArrayList<>(),
					4, UUID.randomUUID(), UUID.randomUUID()));

			assertEquals(4, repository.findById(cReview.getId()).get().getRating().getRatingNumber());
		}

		@Test
		void changeReviewRatingTest() {
			assertEquals(2.5, this.savedReview.getRating().getRatingNumber());

			service.handle(new ChangeRating(savedReview.getId(), 4.8));

			assertEquals(4.8, this.repository.findById(savedReview.getId()).get().getRating().getRatingNumber());
		}

		@Test
		void deleteReviewTest() {
			Review tempReview = service.handle(new MakeReview("desc", new ArrayList<>(), new ArrayList<>(),
					4, UUID.randomUUID(), UUID.randomUUID()));
			service.handle(new DeleteReview(tempReview.getId()));

			assertTrue(this.repository.findById(tempReview.getId()).isEmpty());
		}
}
