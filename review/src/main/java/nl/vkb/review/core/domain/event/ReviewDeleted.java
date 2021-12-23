package nl.vkb.review.core.domain.event;

import lombok.Getter;

import java.util.UUID;

@Getter
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
