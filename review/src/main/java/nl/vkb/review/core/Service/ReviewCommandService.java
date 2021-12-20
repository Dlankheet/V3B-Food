package nl.vkb.review.core.Service;

import nl.vkb.review.core.Service.Command.DeleteReview;
import nl.vkb.review.core.Service.Command.MakeReview;

import nl.vkb.review.core.domain.Rating;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.domain.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandService {

	private ReviewRepository repository;

	private ReviewCommandService(ReviewRepository repository){
		this.repository = repository;
	}

	public void handle(MakeReview command){
		Rating rating = new Rating(command.getRating());

		Review review = new Review(command.getDesc(), command.getPros(), command.getCons(), rating,
				command.getOrderId(), command.getAccountId());

		this.repository.save(review);
	}


	public void handle(DeleteReview command){
		repository.deleteById(command.id);
	}
}
