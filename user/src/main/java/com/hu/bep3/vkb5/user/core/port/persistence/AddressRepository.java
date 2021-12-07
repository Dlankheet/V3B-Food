package com.hu.bep3.vkb5.user.core.port.persistence;

import com.hu.bep3.vkb5.user.core.domain.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AddressRepository extends MongoRepository<Address, UUID> {
}
