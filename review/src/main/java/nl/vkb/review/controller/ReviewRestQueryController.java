package nl.vkb.review.controller;

import nl.vkb.review.Exception.ReviewNotFoundException;
import nl.vkb.review.ReviewRepository;
import nl.vkb.review.Service.ReviewQueryService;
import nl.vkb.review.domain.Review;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class ReviewRestQueryController {
	private final ReviewQueryService service;

	public ReviewRestQueryController(ReviewQueryService service) {
		this.service = service;
	}

	@GetMapping("review/{id}")
	public Review getReview(UUID id) throws ReviewNotFoundException {
		return this.service.getReview(id);
	}
}
