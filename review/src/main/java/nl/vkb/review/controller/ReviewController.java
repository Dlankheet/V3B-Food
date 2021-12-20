package nl.vkb.review.controller;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.Service.ReviewCommandService;
import nl.vkb.review.Service.ReviewQueryService;
import nl.vkb.review.domain.Review;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public Review getReview(UUID id) throws ReviewNotFoundException {
		return this.queryService.getReview(id);
	}

	@PostMapping("/review/create")
	public void createReview(double rating, String desc, List<String> pros, List<String> cons, UUID oId, UUID aId) {
		this.commandService.makeReview(rating, desc, pros, cons, oId, aId);
	}

	@PostMapping("/review/delete/{id}")
	public void deleteReview(UUID uuid) {this.commandService.deleteReview(uuid);}
}
