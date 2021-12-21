package nl.vkb.review.infrastructure.driver.web;

import nl.vkb.review.core.Service.Command.ChangeRating;
import nl.vkb.review.infrastructure.driver.web.Request.ChangeRatingRequest;
import nl.vkb.review.infrastructure.driver.web.Request.MakeReviewRequest;
import nl.vkb.review.core.domain.Review;
import nl.vkb.review.core.Service.Command.DeleteReview;
import nl.vkb.review.core.Service.Command.MakeReview;
import nl.vkb.review.core.Service.Query.GetReviewById;
import nl.vkb.review.core.Service.ReviewCommandService;
import nl.vkb.review.core.Service.ReviewQueryService;
import nl.vkb.review.core.domain.Exception.ReviewNotFoundException;
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
		return this.commandService.handle(new MakeReview(request.description, request.pros, request.cons,
				request.rating, request.orderId, request.accountId));
	}

	@PostMapping("/rating/{id}/change")
	public void changeRating(@PathVariable UUID id, @Valid @RequestBody ChangeRatingRequest request) {
		this.commandService.handle(new ChangeRating(id, request.rating));
	}

	@DeleteMapping("/{id}/delete")
	public void deleteReview(@PathVariable UUID id) {
		this.commandService.handle(new DeleteReview(id));
	}
}
