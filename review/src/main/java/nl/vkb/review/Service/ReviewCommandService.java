package nl.vkb.review.Service;

import nl.vkb.review.Service.Command.DeleteReview;
import nl.vkb.review.Service.Command.MakeReview;
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
