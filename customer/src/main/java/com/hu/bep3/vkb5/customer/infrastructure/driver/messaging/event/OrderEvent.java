package com.hu.bep3.vkb5.customer.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OrderEvent {
	private UUID eventId;
	private Instant eventDate;
	private String eventKey;
	private UUID orderId;
	private UUID customerId;
}
