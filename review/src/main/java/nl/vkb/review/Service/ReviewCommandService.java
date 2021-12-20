package nl.vkb.review.Service;

import nl.vkb.review.domain.ReviewRepository;
import nl.vkb.review.domain.Rating;
import nl.vkb.review.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewCommandService {

	private ReviewRepository repository;

	private ReviewCommandService(ReviewRepository repository){
		this.repository = repository;
	}

	public void makeReview(double ratingNumber, String description, List<String> pros,List<String> cons){
		Rating rating = new Rating(ratingNumber);
		Review review = new Review(description, pros, cons, rating);
		this.repository.save(review);
	}

	public void deleteReview(UUID id){
		repository.deleteById(id);
	}
}
