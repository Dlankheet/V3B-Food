package nl.vkb.customer.infrastructure.driver.web;

import nl.vkb.customer.core.application.CustomerCommandHandler;
import nl.vkb.customer.core.application.CustomerQueryHandler;
import nl.vkb.customer.core.application.command.AddAddress;
import nl.vkb.customer.core.application.command.ChangeEmail;
import nl.vkb.customer.core.application.command.DeleteCustomer;
import nl.vkb.customer.core.application.command.RegisterCustomer;
import nl.vkb.customer.core.application.query.GetCustomerById;
import nl.vkb.customer.core.domain.exception.AddressAlreadyBoundException;
import nl.vkb.customer.core.domain.exception.EmailAlreadyExistsException;
import nl.vkb.customer.core.domain.exception.InvalidEmailException;
import nl.vkb.customer.core.domain.model.Address;
import nl.vkb.customer.core.domain.model.Customer;
import nl.vkb.customer.infrastructure.driver.web.request.AddAddressRequest;
import nl.vkb.customer.infrastructure.driver.web.request.ChangeEmailRequest;
import nl.vkb.customer.infrastructure.driver.web.request.RegisterCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerCommandHandler commandHandler;
	private final CustomerQueryHandler queryHandler;

	public CustomerController(CustomerCommandHandler commandHandler, CustomerQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@PostMapping
	public Customer registerCustomer(@Valid @RequestBody RegisterCustomerRequest request) {
		return this.commandHandler.handle(
				new RegisterCustomer(request.firstName, request.lastName, request.email)
		);
	}

	@DeleteMapping("/{id}")
	public void unregisterCustomer(@PathVariable UUID id) {
		this.commandHandler.handle(new DeleteCustomer(id));
	}

	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable UUID id){
		return this.queryHandler.handle(new GetCustomerById(id));
	}

	@PostMapping("/{id}/add-address")
	public Customer addAddress(@PathVariable UUID id, @Valid @RequestBody AddAddressRequest request){
		return this.commandHandler.handle(
			new AddAddress(id, new Address(request.street, request.number, request.additionalLetter, request.postalCode))
		);
	}

	@PostMapping("/{id}/change-email")
	public Customer changeEmail(@PathVariable UUID id, @Valid @RequestBody ChangeEmailRequest request){
		return this.commandHandler.handle(
				new ChangeEmail(id, request.newEmail)
		);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleDuplicate(EmailAlreadyExistsException exception) {
		return new ResponseEntity<>("A customer with that email already exists", HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleDuplicate(InvalidEmailException exception) {
		return new ResponseEntity<>("That is not a valid email-address", HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleDuplicate(AddressAlreadyBoundException exception) {
		return new ResponseEntity<>("That address is already bound to this customer", HttpStatus.CONFLICT);
	}
}
