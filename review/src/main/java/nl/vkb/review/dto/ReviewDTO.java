package nl.vkb.review.dto;

import nl.vkb.review.domain.Rating;

import java.util.List;
import java.util.UUID;

public class ReviewDTO {
	public UUID id;
	public String description;
	public List<String> pros;
	public List<String> cons;
	public Rating rating;
}
