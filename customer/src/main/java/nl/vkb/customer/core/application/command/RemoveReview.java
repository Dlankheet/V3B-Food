package nl.vkb.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RemoveReview {
	private final UUID review;
}
