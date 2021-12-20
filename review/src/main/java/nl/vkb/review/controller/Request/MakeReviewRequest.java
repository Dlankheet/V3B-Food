package nl.vkb.review.controller.Request;

import nl.vkb.review.core.domain.Rating;

import java.util.List;
import java.util.UUID;

public class MakeReviewRequest {

	public String description;
	public List<String> pros;
	public List<String> cons;
	public double rating;
	public UUID orderId;
	public UUID accountId;
}
