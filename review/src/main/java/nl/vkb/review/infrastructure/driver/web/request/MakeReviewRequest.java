package nl.vkb.review.infrastructure.driver.web.request;

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
