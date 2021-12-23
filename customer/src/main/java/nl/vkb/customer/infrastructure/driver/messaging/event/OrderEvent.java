package nl.vkb.customer.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class OrderEvent {
	public UUID eventId;
	public Instant eventDate;
	public String eventKey;
	public UUID orderId;
	public UUID customerId;
}