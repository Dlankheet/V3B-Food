package com.hu.bep3.vkb5.customer.core.application;

import com.hu.bep3.vkb5.customer.core.application.command.RegisterCustomer;
import com.hu.bep3.vkb5.customer.core.domain.exception.EmailAlreadyExistsException;
import com.hu.bep3.vkb5.customer.core.domain.model.Customer;
import com.hu.bep3.vkb5.customer.core.port.persistence.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandHandler {
	private final CustomerRepository repository;
//	private final CustomerEventPublisher eventPublisher;

	public CustomerCommandHandler(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer handle(RegisterCustomer command) throws EmailAlreadyExistsException{
		if(emailAlreadyExists(command.getEmail())){
			throw new EmailAlreadyExistsException(command.getEmail());
		}
		Customer customer = new Customer(command.getFirstName(), command.getLastName(), command.getEmail());
		this.repository.save(customer);
		return customer;
	}

	private boolean emailAlreadyExists(String email){
		return this.repository.findCustomerByEmailEquals(email).isPresent();
	}
}
