package nl.vkb.customer.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderFood {
	private final UUID orderId;
	private final UUID customerId;
}
