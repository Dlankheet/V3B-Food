package nl.vkb.review.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {
}