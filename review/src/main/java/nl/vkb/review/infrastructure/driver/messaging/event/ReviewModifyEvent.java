package nl.vkb.review.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class ReviewModifyEvent {
	public UUID eventId;
	public String eventKey;
	public Instant eventDate;
	public UUID review;

}
