package nl.hu.v3bfood_dishes.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class ExampleIncommingEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public UUID job;
    public String keyword;
}
