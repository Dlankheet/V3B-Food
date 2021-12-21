package nl.vkb.review.core.domain;

import lombok.Getter;
import nl.vkb.review.core.domain.event.ReviewEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Review {
	@Id
	private final UUID id;
	private final String description;
	private final List<String> pros;
	private final List<String> cons;
	private final Rating rating;
	private final UUID orderId;
	private final UUID accountId;
	@Transient
	private List<ReviewEvent> events = new ArrayList<>();

	public Review(String description, List<String> pros, List<String> cons, Rating rating, UUID orderId, UUID accountId){
		this.id = UUID.randomUUID();
		this.description = description;
		this.pros = pros;
		this.cons = cons;
		this.rating = rating;
		this.orderId = orderId;
		this.accountId = accountId;
	}

	public void clearEvents() {
		this.events.clear();
	}
}
