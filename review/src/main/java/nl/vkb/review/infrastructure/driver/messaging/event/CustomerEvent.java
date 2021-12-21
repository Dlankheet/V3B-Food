package nl.vkb.review.infrastructure.driver.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CustomerEvent {
	private final UUID eventId;
	private final String eventKey;
	private final Instant eventDate;
	private final UUID customerId;
}
