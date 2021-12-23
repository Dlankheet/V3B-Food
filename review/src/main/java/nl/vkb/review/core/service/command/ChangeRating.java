package nl.vkb.review.core.service.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeRating {
	public final UUID id;
	public final double rating;
}
