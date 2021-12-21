package nl.vkb.customer.core.application;

import nl.vkb.customer.core.domain.event.CustomerDeleted;
import nl.vkb.customer.core.domain.event.CustomerEvent;
import nl.vkb.customer.core.domain.exception.AddressAlreadyBoundException;
import nl.vkb.customer.core.domain.exception.CustomerNotFoundException;
import nl.vkb.customer.core.domain.exception.EmailAlreadyExistsException;
import nl.vkb.customer.core.domain.exception.InvalidEmailException;
import nl.vkb.customer.core.domain.model.Address;
import nl.vkb.customer.core.domain.model.Customer;
import nl.vkb.customer.core.port.messaging.CustomerEventPublisher;
import nl.vkb.customer.core.port.persistence.CustomerRepository;
import lombok.AllArgsConstructor;
import nl.vkb.customer.core.application.command.*;
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

	public void handle(DeleteCustomer command) {
		Customer customer = this.getCustomerById(command.getCustomerId());
		repository.delete(customer);
		customer.listEvents().add(new CustomerDeleted(command.getCustomerId()));
		publishEventsFor(customer);
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

	public void handle(OrderFood command) {
		Customer customer = this.getCustomerById(command.getCustomerId());
		customer.orderFood(command.getOrderId());
		repository.save(customer);
	}

	public void handle(ReviewOrder command) {
		Customer customer = this.getCustomerById(command.getCustomerId());
		customer.reviewOrder(command.getReviewId());
		repository.save(customer);
	}

	private boolean emailAlreadyExists(String email){
		return this.repository.findCustomerByEmail(email).isPresent();
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
