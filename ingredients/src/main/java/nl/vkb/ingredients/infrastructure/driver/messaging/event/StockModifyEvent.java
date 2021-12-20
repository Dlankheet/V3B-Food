package nl.vkb.ingredients.infrastructure.driver.messaging.event;


import java.time.Instant;
import java.util.UUID;

public class StockModifyEvent {
	public UUID eventId;
	public String eventKey;
	public Instant eventDate;
	public UUID ingredient;
	public int amount;
}
