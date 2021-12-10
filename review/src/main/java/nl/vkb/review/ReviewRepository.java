package nl.vkb.review;

import nl.vkb.review.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
}
