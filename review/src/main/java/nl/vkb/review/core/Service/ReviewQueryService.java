package nl.vkb.review.core.Service;

import nl.vkb.review.core.domain.Exception.ReviewNotFoundException;
import nl.vkb.review.core.Service.Query.GetReviewById;
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
