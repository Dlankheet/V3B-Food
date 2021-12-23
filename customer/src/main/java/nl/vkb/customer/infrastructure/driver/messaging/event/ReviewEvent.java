package nl.vkb.customer.infrastructure.driver.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReviewEvent {
	private UUID eventId;
	private Instant eventDate;
	private String eventKey;
	private UUID account;
	private UUID review;
}
