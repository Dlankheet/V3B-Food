package com.hu.bep3.vkb5.customer.core.port.persistence;

import com.hu.bep3.vkb5.customer.core.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {
	Optional<Customer> findCustomerByEmail(String email);
}
