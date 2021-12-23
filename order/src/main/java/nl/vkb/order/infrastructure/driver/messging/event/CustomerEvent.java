package nl.vkb.order.infrastructure.driver.messging.event;

import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@ToString
public class CustomerEvent {
    public UUID eventId;
    public Instant eventDate;
    public String eventKey;
    public UUID customerId;
}
