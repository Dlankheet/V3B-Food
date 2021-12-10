package nl.vkb.review.controller;

import nl.vkb.review.Service.ReviewCommandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewRestCommandController {
	private final ReviewCommandService service;

	public ReviewRestCommandController(ReviewCommandService service) {
		this.service = service;
	}

	@PostMapping("/review/create")
	public void createReview(double rating, String description, List<String> pros, List<String> cons) {
		this.service.makeReview(rating, description, pros, cons);
	}
}
