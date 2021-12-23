package nl.vkb.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReviewOrder {
	private final UUID review;
	private final UUID account;
}
