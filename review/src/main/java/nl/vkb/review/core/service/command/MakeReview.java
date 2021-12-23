package nl.vkb.review.core.service.command;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;


@Getter
@AllArgsConstructor
public class MakeReview {
	private final String desc;
	private final List<String> pros;
	private final List<String> cons;
	private final double rating;
	private final UUID orderId;
	private final UUID accountId;

}
