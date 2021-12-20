package nl.vkb.review.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class ReviewEvent {
	public final UUID eventId = UUID.randomUUID();
	public final Instant eventDate = Instant.now();

	public abstract String getEventKey();


}
