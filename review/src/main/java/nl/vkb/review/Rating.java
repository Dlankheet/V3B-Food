package nl.vkb.review;

import lombok.Data;

import java.util.UUID;

@Data
public class Rating {
	private UUID id;
	private double rating;
}
