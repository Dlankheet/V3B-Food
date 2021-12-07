package com.hu.bep3.vkb5.user.core.port.persistence;

import com.hu.bep3.vkb5.user.core.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CustomerRepository extends MongoRepository<Customer, UUID> {

}
