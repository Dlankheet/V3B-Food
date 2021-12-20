package nl.vkb.review.Service;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.domain.ReviewRepository;
import nl.vkb.review.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewQueryService {
	private ReviewRepository repository;

	public ReviewQueryService(ReviewRepository repository){
		this.repository = repository;
	}

	public Review getReview(UUID id) {
		return repository.findById(id).orElseThrow(
				() -> new ReviewNotFoundException("Does not exist")
		);
	}

	public List<Review> getAllReviews() {
		return repository.findAll();
	}
}
