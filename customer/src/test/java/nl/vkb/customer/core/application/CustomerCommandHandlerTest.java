package nl.vkb.customer.core.application;

import nl.vkb.customer.core.application.command.*;
import nl.vkb.customer.core.domain.exception.AddressAlreadyBoundException;
import nl.vkb.customer.core.domain.exception.CustomerNotFoundException;
import nl.vkb.customer.core.domain.exception.EmailAlreadyExistsException;
import nl.vkb.customer.core.domain.exception.InvalidEmailException;
import nl.vkb.customer.core.domain.model.Address;
import nl.vkb.customer.core.domain.model.Customer;
import nl.vkb.customer.core.port.messaging.CustomerEventPublisher;
import nl.vkb.customer.core.port.persistence.CustomerRepository;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerCommandHandlerTest {
	private CustomerCommandHandler handler;
	private CustomerRepository repository;
	private CustomerEventPublisher publisher;

	@BeforeEach
	void setup(){
		this.repository = mock(CustomerRepository.class);
		this.publisher = mock(CustomerEventPublisher.class);
		this.handler = new CustomerCommandHandler(repository, publisher);
	}

	@Test
	@DisplayName("Register a new customer and persist it")
	void registerCustomerHappyFlow(){
		RegisterCustomer command = new RegisterCustomer("john", "doe", "john.doe@gmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.save(any(Customer.class)))
				.thenReturn(mockedCustomer);
		assertDoesNotThrow(()-> assertEquals(mockedCustomer, handler.handle(command)));
	}

	@Test
	@DisplayName("Registering with an existing email should throw an exception")
	void registerCustomerWithExistingEmail(){
		RegisterCustomer command = new RegisterCustomer("john", "doe", "john.doe@gmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findCustomerByEmail(any(String.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		assertThrows(EmailAlreadyExistsException.class, ()->{
			handler.handle(command);
		});
	}

	@Test
	@DisplayName("Deleting a customer should clear it's events")
	void deleteExistingCustomer(){
		DeleteCustomer command = new DeleteCustomer(UUID.randomUUID());
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		handler.handle(command);
		assertEquals(0, mockedCustomer.listEvents().size());
	}

	@Test
	@DisplayName("Deleting a nonexistent customer should throw an exception")
	void deleteNonExistingCustomer(){
		DeleteCustomer command = new DeleteCustomer(UUID.randomUUID());
		when(repository.findById(any(UUID.class)))
				.thenReturn(Optional.empty());
		assertThrows(CustomerNotFoundException.class, ()->{
			handler.handle(command);
		});
	}

	@Test
	@DisplayName("Add an address to an existing user")
	void addAddressHappyFlow(){
		AddAddress command = new AddAddress(UUID.randomUUID(), new Address("Valley View Road", 54, "B", "1324FH"));
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		handler.handle(command);
		assertEquals(command.getAddress(), mockedCustomer.getAddresses().toArray()[0]);
		assertEquals(1, mockedCustomer.getAddresses().size());
	}

	@Test
	@DisplayName("Adding an address that is already bound to the Customer should throw an exception")
	void addAddressAlreadyBound(){
		AddAddress command = new AddAddress(UUID.randomUUID(), new Address("Valley View Road", 54, "B", "1324FH"));
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		mockedCustomer.addAddress(command.getAddress());
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		assertThrows(AddressAlreadyBoundException.class, ()->{
			handler.handle(command);
		});
	}

	@Test
	@DisplayName("Change the email of a customer")
	void changeEmailHappyFlow(){
		ChangeEmail command = new ChangeEmail(UUID.randomUUID(), "johan.doey@gmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		handler.handle(command);
		assertDoesNotThrow(()-> {
			assertEquals(command.getNewEmail(), mockedCustomer.getEmail());
			assertEquals(0, mockedCustomer.listEvents().size());
		});
	}

	@Test
	@DisplayName("Change the email with an already existing email")
	void changeEmailWithExistingEmail(){
		ChangeEmail command = new ChangeEmail(UUID.randomUUID(), "john.doe@gmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		when(repository.findCustomerByEmail(any(String.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		assertThrows(EmailAlreadyExistsException.class, ()->{
			handler.handle(command);
		});
	}

	@Test
	@DisplayName("Change the email with an already existing email")
	void changeEmailWithInvalidEmail(){
		ChangeEmail command = new ChangeEmail(UUID.randomUUID(), "john.doe#hotmail.com");
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		assertThrows(InvalidEmailException.class, ()->{
			handler.handle(command);
		});
	}

	@Test
	@DisplayName("Order some food")
	void orderFood(){
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		repository.save(mockedCustomer);
		UUID orderId = UUID.randomUUID();
		UUID customerId = repository.findById(UUID.randomUUID()).get().getId();
		handler.handle(new OrderFood(customerId, orderId));
		assertEquals(1,mockedCustomer.getOrderHistory().size());
	}

	@Test
	@DisplayName("Review an order")
	void reviewOrder(){
		Customer mockedCustomer = new Customer("john", "doe", "john.doe@gmail.com");
		when(repository.findById(any(UUID.class)))
				.thenReturn(java.util.Optional.of(mockedCustomer));
		repository.save(mockedCustomer);
		UUID reviewId = UUID.randomUUID();
		UUID customerId = repository.findById(UUID.randomUUID()).get().getId();
		handler.handle(new ReviewOrder(customerId, reviewId));
		assertEquals(1,mockedCustomer.getReviews().size());
	}
}
