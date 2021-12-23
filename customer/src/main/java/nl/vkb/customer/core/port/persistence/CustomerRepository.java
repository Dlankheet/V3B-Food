package nl.vkb.customer.core.port.persistence;

import nl.vkb.customer.core.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
	Optional<Customer> findCustomerByEmail(String email);
	Optional<Customer> getCustomerByReviewsContains(UUID id);
}
