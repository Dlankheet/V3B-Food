package nl.vkb.review;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Rating {
	@Id
	private UUID id;
	private double rating;
}
