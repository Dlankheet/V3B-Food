package nl.vkb.review.dto;

import nl.vkb.review.domain.Rating;
import nl.vkb.review.domain.Review;

import java.util.List;
import java.util.UUID;

public class ReviewDTO {
	public UUID id;
	public String description;
	public List<String> pros;
	public List<String> cons;
	public Rating rating;
	public UUID orderId;
	public UUID accountId;

	public ReviewDTO(Review review) {
		this.id = review.getId();
		this.description = review.getDescription();
		this.pros = review.getPros();
		this.cons = review.getCons();
		this.rating = review.getRating();
		this.orderId = review.getOrderId();
		this.accountId = review.getAccountId();

	}
}