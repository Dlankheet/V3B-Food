package com.hu.bep3.vkb5.customer.infrastructure.driver.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReviewEvent {
	public UUID eventId;
	public Instant eventDate;
	public String eventKey;
	public UUID reviewId;
	public UUID customerId;
}
