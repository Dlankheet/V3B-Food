package nl.vkb.dishes.infrastructure.driver.messaging.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderAcceptedEvent {
	private final UUID eventId;
	private final String eventKey;
	private final Instant eventDate;
	private final UUID order;
	private final UUID customer;
	private final List<UUID> dishes;
}
