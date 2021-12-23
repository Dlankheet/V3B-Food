package nl.vkb.review.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import nl.vkb.review.core.domain.event.ReviewCreated;
import nl.vkb.review.core.domain.event.ReviewEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Document
public class Review {
	@Id
	private UUID id;
	private String description;
	private List<String> pros;
	private List<String> cons;
	private Rating rating;
	private UUID orderId;
	private UUID accountId;
	@Transient
	@Getter(AccessLevel.NONE)
	private List<ReviewEvent> events = new ArrayList<>();

	public Review(String description, List<String> pros, List<String> cons, Rating rating, UUID orderId, UUID accountId){
		this.id = UUID.randomUUID();
		this.description = description;
		this.pros = pros;
		this.cons = cons;
		this.rating = rating;
		this.orderId = orderId;
		this.accountId = accountId;
		this.events.add(new ReviewCreated(this.id, this.accountId));
	}


	public void clearEvents() {
		this.events.clear();
	}

	public List<ReviewEvent> returnEvents() { return events; }
}
