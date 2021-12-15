package com.hu.bep3.vkb5.customer.infrastructure.driver.web;

import com.hu.bep3.vkb5.customer.core.application.CustomerCommandHandler;
import com.hu.bep3.vkb5.customer.core.application.CustomerQueryHandler;
import com.hu.bep3.vkb5.customer.core.application.command.AddAddress;
import com.hu.bep3.vkb5.customer.core.application.command.RegisterCustomer;
import com.hu.bep3.vkb5.customer.core.application.query.GetCustomerById;
import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.EmailAlreadyExistsException;
import com.hu.bep3.vkb5.customer.core.domain.model.Address;
import com.hu.bep3.vkb5.customer.core.domain.model.Customer;
import com.hu.bep3.vkb5.customer.infrastructure.driver.request.AddAddressRequest;
import com.hu.bep3.vkb5.customer.infrastructure.driver.request.RegisterCustomerRequest;
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

	@ExceptionHandler
	public ResponseEntity<String> handleDuplicate(EmailAlreadyExistsException exception) {
		return new ResponseEntity<>("A customer with that email already exists", HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<String> handleDuplicate(AddressAlreadyBoundException exception) {
		return new ResponseEntity<>("That address is already bound to this customer", HttpStatus.CONFLICT);
	}
}
