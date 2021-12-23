package nl.vkb.review.core.domain.event;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewCreated extends ReviewEvent{
	private final UUID review;
	private final UUID account;

	public ReviewCreated(UUID review, UUID account) {
		this.review = review;
		this.account = account;
	}
	@Override
	public String getEventKey() {
		return "event.review.created";
	}
}
