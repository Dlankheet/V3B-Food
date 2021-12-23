package nl.vkb.customer.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ReviewEvent {
	private UUID eventId;
	private Instant eventDate;
	private String eventKey;
	private UUID review;
	private UUID customer;
}
