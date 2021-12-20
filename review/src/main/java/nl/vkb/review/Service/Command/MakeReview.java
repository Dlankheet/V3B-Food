package nl.vkb.review.Service.Command;


import lombok.Data;
import nl.vkb.review.domain.Rating;

import java.util.List;
import java.util.UUID;


@Data
public class MakeReview {
	private final String desc;
	private final List<String> pros;
	private final List<String> cons;
	private final double rating;
	private final UUID orderId;
	private final UUID accountId;

}
