package com.hu.bep3.vkb5.customer.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class CustomerEvent {
	private final UUID eventId = UUID.randomUUID();
	private final Instant eventDate = Instant.now();

	public abstract String getEventKey();
}
