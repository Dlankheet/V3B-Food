package com.hu.bep3.vkb5.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReviewOrder {
	private final UUID customerId;
	private final UUID reviewId;
}
