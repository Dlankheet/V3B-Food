package nl.vkb.review.core.domain;

import nl.vkb.review.core.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
}