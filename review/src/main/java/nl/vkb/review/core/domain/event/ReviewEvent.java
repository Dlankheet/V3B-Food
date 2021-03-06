package nl.vkb.review.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class ReviewEvent {
	public final UUID eventId = UUID.randomUUID();
	public final Instant eventDate = Instant.now();

	public abstract String getEventKey();
}
