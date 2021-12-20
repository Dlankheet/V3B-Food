package nl.vkb.review.Service;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.Service.Query.GetReviewById;
import nl.vkb.review.domain.ReviewRepository;
import nl.vkb.review.domain.Review;
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
