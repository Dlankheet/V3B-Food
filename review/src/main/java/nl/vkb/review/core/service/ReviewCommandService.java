package nl.vkb.review.core.service;

import nl.vkb.review.core.service.command.ChangeRating;
import nl.vkb.review.core.service.command.DeleteAllReviewsByCustomer;
import nl.vkb.review.core.service.command.DeleteReview;
import nl.vkb.review.core.service.command.MakeReview;

import nl.vkb.review.core.domain.exception.ReviewNotFoundException;
import nl.vkb.review.core.domain.Rating;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.domain.event.ReviewDeleted;
import nl.vkb.review.core.domain.event.ReviewEvent;
import nl.vkb.review.core.port.messaging.ReviewEventPublisher;
import nl.vkb.review.core.port.storage.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewCommandService {
	private final ReviewRepository repository;
	private final ReviewEventPublisher eventPublisher;

	public ReviewCommandService(ReviewRepository repository, ReviewEventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
	}

	public Review handle(MakeReview command){
		Rating rating = new Rating(command.getRating());

		Review review = new Review(command.getDesc(), command.getPros(), command.getCons(), rating,
				command.getOrderId(), command.getAccountId());

		this.publishEventsFor(review);
		return this.repository.save(review);
	}

	public void handle(DeleteReview command){
		Review review = getReviewbyId(command.id);
		review.getEvents().add(new ReviewDeleted(review.getAccountId()));
		publishEventsFor(review);
		repository.deleteById(command.id);
	}

	public void handle(DeleteAllReviewsByCustomer command) {
		List<Review> reviewList = repository.findReviewsByAccountId(command.id);
		this.publishListEventsFor(reviewList);
		repository.deleteAllByAccountId(command.id);
	}

	public void handle(ChangeRating command) {
		Review review = getReviewbyId(command.id);
		review.getRating().changeRating(command.rating);

		publishEventsFor(review);
		repository.save(review);
	}

	private Review getReviewbyId(UUID id) {
		return this.repository.findById(id).orElseThrow(
				() -> new ReviewNotFoundException("Does not exist")
		);
	}

	private void publishEventsFor(Review review) {
		List<ReviewEvent> events = review.getEvents();
		events.forEach(eventPublisher::publish);
		review.clearEvents();
	}

	private void publishListEventsFor(List<Review> reviewList) {
		reviewList.forEach(this::publishEventsFor);
	}
}
