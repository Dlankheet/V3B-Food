package com.hu.bep3.vkb5.customer.core.application;

import com.hu.bep3.vkb5.customer.core.application.command.AddAddress;
import com.hu.bep3.vkb5.customer.core.application.command.ChangeEmail;
import com.hu.bep3.vkb5.customer.core.application.command.RegisterCustomer;
import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;
import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.CustomerNotFoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.EmailAlreadyExistsException;
import com.hu.bep3.vkb5.customer.core.domain.exception.InvalidEmailException;
import com.hu.bep3.vkb5.customer.core.domain.model.Address;
import com.hu.bep3.vkb5.customer.core.domain.model.Customer;
import com.hu.bep3.vkb5.customer.core.port.messaging.CustomerEventPublisher;
import com.hu.bep3.vkb5.customer.core.port.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerCommandHandler {
	private final CustomerRepository repository;
	private final CustomerEventPublisher eventPublisher;

	public Customer handle(RegisterCustomer command) throws EmailAlreadyExistsException, InvalidEmailException {
		if(this.emailAlreadyExists(command.getEmail())){
			throw new EmailAlreadyExistsException(command.getEmail());
		}
		Customer customer = new Customer(command.getFirstName(), command.getLastName(), command.getEmail());
		this.repository.save(customer);
		return customer;
	}

	public Customer handle(AddAddress command) throws AddressAlreadyBoundException {
		Customer customer = this.getCustomerById(command.getCustomerId());
		Address commandAddress = command.getAddress();
		customer.addAddress(commandAddress);
		repository.save(customer);
		return customer;
	}

	public Customer handle(ChangeEmail command) throws InvalidEmailException{
		Customer customer = this.getCustomerById(command.getCustomerId());
		customer.changeEmail(command.getNewEmail());
		repository.save(customer);
		this.publishEventsFor(customer);
		return customer;
	}

	private boolean emailAlreadyExists(String email){
		System.out.println(email);
		return this
				.repository
				.findCustomerByEmailEquals(email)
				.isPresent();
	}

	private Customer getCustomerById(UUID id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id.toString()));
	}

	private void publishEventsFor(Customer customer) {
		List<CustomerEvent> events = customer.listEvents();
		events.forEach(eventPublisher::publish);
		customer.clearEvents();
	}
}
