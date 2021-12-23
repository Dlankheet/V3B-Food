package nl.vkb.order.infrastructure.driver.messging.event;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@ToString
@AllArgsConstructor
public class CustomerEvent {
    public final UUID eventId;
    public final Instant eventDate;
    public final String eventKey;
    public final UUID customerId;
}
