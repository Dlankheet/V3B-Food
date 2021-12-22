package nl.vkb.order.infrastructure.driver.messging.event;

import java.time.Instant;
import java.util.UUID;

public class OrderEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID customer;
    public UUID dish;
}
