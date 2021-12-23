package nl.vkb.review.infrastructure.driver.web;

import nl.vkb.review.core.service.command.ChangeRating;
import nl.vkb.review.core.domain.exception.ReviewRatingException;
import nl.vkb.review.infrastructure.driver.web.request.ChangeRatingRequest;
import nl.vkb.review.infrastructure.driver.web.request.MakeReviewRequest;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.service.command.DeleteReview;
import nl.vkb.review.core.service.command.MakeReview;
import nl.vkb.review.core.service.query.GetReviewById;
import nl.vkb.review.core.service.ReviewCommandService;
import nl.vkb.review.core.service.ReviewQueryService;
import nl.vkb.review.core.domain.exception.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {
	private final ReviewCommandService commandService;
	private final ReviewQueryService queryService;

	public ReviewController(ReviewCommandService commandService, ReviewQueryService queryService) {
		this.commandService = commandService;
		this.queryService = queryService;
	}

	@GetMapping("/{id}")
	public Review getReview(@PathVariable UUID id) throws ReviewNotFoundException {
		return this.queryService.handle(new GetReviewById(id));
	}

	@PostMapping("/create")
	public Review createReview(@Valid @RequestBody MakeReviewRequest request) {
		return this.commandService.handle(new MakeReview(request.getDescription(), request.getPros(), request.getCons(),
				request.getRating(), request.getOrderId(), request.getAccountId()));
	}

	@PostMapping("/rating/{id}/change")
	public void changeRating(@PathVariable UUID id, @Valid @RequestBody ChangeRatingRequest request) {
		this.commandService.handle(new ChangeRating(id, request.getRating()));
	}

	@DeleteMapping("/{id}/delete")
	public void deleteReview(@PathVariable UUID id) {
		this.commandService.handle(new DeleteReview(id));
	}

	@ExceptionHandler
	public ResponseEntity<Void> reviewNotFound(ReviewNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ExceptionHandler
	public ResponseEntity<Void> ratingNotCorrect(ReviewRatingException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
