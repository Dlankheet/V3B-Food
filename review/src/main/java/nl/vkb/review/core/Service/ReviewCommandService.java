package nl.vkb.review.core.Service;

import nl.vkb.review.core.Service.Command.DeleteReview;
import nl.vkb.review.core.Service.Command.MakeReview;

import nl.vkb.review.core.domain.Rating;
import nl.vkb.review.core.domain.Review;
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

	public void handle(MakeReview command){
		Rating rating = new Rating(command.getRating());

		Review review = new Review(command.getDesc(), command.getPros(), command.getCons(), rating,
				command.getOrderId(), command.getAccountId());

		this.publishEventsFor(review);
		this.repository.save(review);
	}

	public void handle(DeleteReview command){
		publishEventsFor(getReviewbyId(command.id));
		repository.deleteById(command.id);
	}

	private Review getReviewbyId(UUID id) {
		return this.repository.findById(id).get();
	}

	private void publishEventsFor(Review review) {
		List<ReviewEvent> events = review.getEvents();
		events.forEach(eventPublisher::publish);
		review.clearEvents();
	}
}
