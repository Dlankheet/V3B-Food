package nl.vkb.customer.infrastructure.driver.messaging.event;

import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class OrderEvent {
	private UUID eventId;
	private Instant eventDate;
	private String eventKey;
	private UUID order;
	private UUID customer;
}
