package nl.vkb.review.core.port.storage;

import nl.vkb.review.core.domain.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends MongoRepository<Review, UUID> {

	List<Review> findReviewsByAccountId(UUID id);
	void deleteAllByAccountId(UUID id);

}