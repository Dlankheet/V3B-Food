package nl.vkb.review.core.service;

import nl.vkb.review.core.domain.exception.ReviewNotFoundException;
import nl.vkb.review.core.service.query.GetReviewById;
import nl.vkb.review.core.port.storage.ReviewRepository;
import nl.vkb.review.core.domain.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewQueryService {
	private ReviewRepository repository;

	public ReviewQueryService(ReviewRepository repository){
		this.repository = repository;
	}

	public Review handle(GetReviewById command) {
		return repository.findById(command.id).orElseThrow(
				() -> new ReviewNotFoundException("Does not exist")
		);
	}
}
