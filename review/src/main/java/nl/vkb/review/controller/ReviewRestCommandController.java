package nl.vkb.review.controller;

import nl.vkb.review.ReviewRepository;
import nl.vkb.review.domain.Rating;
import nl.vkb.review.domain.Review;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewRestCommandController {
	private final ReviewRepository repository;

	public ReviewRestCommandController(ReviewRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/review/create")
	public void createReview(double rating, String description, List<String> pro, List<String> con) {

	}
}
