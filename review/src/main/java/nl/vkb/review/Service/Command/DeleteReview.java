package nl.vkb.review.Service.Command;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteReview {
	public final UUID id;
}
