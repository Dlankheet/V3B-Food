package nl.vkb.customer.core.application;

import nl.vkb.customer.core.application.command.RegisterCustomer;
import nl.vkb.customer.core.domain.model.Customer;
import nl.vkb.customer.core.port.messaging.CustomerEventPublisher;
import nl.vkb.customer.core.port.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerCommandHandlerTest {
	private static CustomerCommandHandler handler;
	private static CustomerRepository repository;
	private static CustomerEventPublisher publisher;

	@BeforeAll
	static void setup(){
		repository = mock(CustomerRepository.class);
		publisher = mock(CustomerEventPublisher.class);
		handler = new CustomerCommandHandler(repository, publisher);
	}

	@Test
	@DisplayName("Register a new customer and persist it")
	void registerCustomerHappyFlow(){
		RegisterCustomer command = new RegisterCustomer("john", "doe", "john.doe@gmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		Customer expectedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.save(any(Customer.class)))
				.thenReturn(mockedCustomer);
		assertDoesNotThrow(()-> assertEquals(expectedCustomer, handler.handle(command)));
	}
}
