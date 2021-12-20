package nl.vkb.review.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Rating {
	@Id
	private UUID id;
	private double ratingNumber;

	public Rating(double ratingNumber) {this.ratingNumber = ratingNumber;}
}
