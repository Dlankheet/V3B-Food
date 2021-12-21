package nl.vkb.customer.core.application;

import nl.vkb.customer.core.application.query.GetCustomerById;
import nl.vkb.customer.core.domain.exception.CustomerNotFoundException;
import nl.vkb.customer.core.domain.model.Customer;
import nl.vkb.customer.core.port.persistence.CustomerRepository;
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
