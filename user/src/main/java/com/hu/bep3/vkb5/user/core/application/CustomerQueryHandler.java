package com.hu.bep3.vkb5.user.core.application;

import com.hu.bep3.vkb5.user.core.application.query.GetCustomerById;
import com.hu.bep3.vkb5.user.core.domain.exception.CustomerNotFoundException;
import com.hu.bep3.vkb5.user.core.domain.model.Customer;
import com.hu.bep3.vkb5.user.core.port.persistence.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerQueryHandler {
	private final CustomerRepository repository;

	public CustomerQueryHandler(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer handle(GetCustomerById query){
		return this.repository.findById(query.getId())
				.orElseThrow(() -> new CustomerNotFoundException(query.getId().toString()));
	}
}
