package nl.vkb.review.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangeRatingRequest {
	private final double rating;
}
