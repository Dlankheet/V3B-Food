package nl.vkb.review.controller;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.Service.Command.MakeReview;
import nl.vkb.review.Service.ReviewCommandService;
import nl.vkb.review.Service.ReviewQueryService;
import nl.vkb.review.controller.Request.MakeReviewRequest;
import nl.vkb.review.domain.Review;
import nl.vkb.review.dto.ReviewDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class ReviewController {
	private final ReviewCommandService commandService;
	private final ReviewQueryService queryService;

	public ReviewController(ReviewCommandService commandService, ReviewQueryService queryService) {
		this.commandService = commandService;
		this.queryService = queryService;
	}

	@GetMapping("review/{id}")
	public ReviewDTO getReview(UUID id) throws ReviewNotFoundException {
		return new ReviewDTO(this.queryService.getReview(id));
	}

	@PostMapping("/review/create")
	public void createReview(@Valid @RequestBody MakeReviewRequest request) {
		this.commandService.handle(new MakeReview(request.description, request.pros, request.cons,
				request.rating, request.orderId, request.accountId));
	}

	@PostMapping("/review/delete/{id}")
	public void deleteReview(UUID uuid) {this.commandService.deleteReview(uuid);}
}
