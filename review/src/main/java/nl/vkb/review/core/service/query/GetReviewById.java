package nl.vkb.review.core.service.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetReviewById {
	public final UUID id;
}
