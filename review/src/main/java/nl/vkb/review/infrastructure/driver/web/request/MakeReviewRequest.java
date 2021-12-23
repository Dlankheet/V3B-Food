package nl.vkb.review.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class MakeReviewRequest {
	private final String description;
	private final List<String> pros;
	private final List<String> cons;
	private final double rating;
	private final UUID orderId;
	private final UUID accountId;
}
