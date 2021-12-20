package nl.vkb.review.core.Service.Command;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteReview {
	public final UUID id;
}
