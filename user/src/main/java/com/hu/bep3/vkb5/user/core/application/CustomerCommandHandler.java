package com.hu.bep3.vkb5.user.core.application;

import com.hu.bep3.vkb5.user.core.application.command.RegisterCustomer;
import com.hu.bep3.vkb5.user.core.domain.model.Customer;
import com.hu.bep3.vkb5.user.core.port.persistence.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandHandler {
	private final CustomerRepository repository;
//	private final CustomerEventPublisher eventPublisher;


	public CustomerCommandHandler(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer handle(RegisterCustomer command){
		Customer customer = new Customer(command.getFirstName(), command.getLastName(), command.getEmail());
		this.repository.save(customer);
		return customer;
	}
}
