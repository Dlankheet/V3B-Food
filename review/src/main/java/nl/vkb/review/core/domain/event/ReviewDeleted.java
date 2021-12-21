package nl.vkb.review.core.domain.event;

import java.util.UUID;

public class ReviewDeleted extends ReviewEvent {
	private final UUID review;

	public ReviewDeleted(UUID review) {
		this.review = review;
	}

	@Override
	public String getEventKey() {
		return "event.review.deleted";
	}
}
