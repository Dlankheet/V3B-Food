package nl.vkb.review.core.Service.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteReview {
	public final UUID id;
}
