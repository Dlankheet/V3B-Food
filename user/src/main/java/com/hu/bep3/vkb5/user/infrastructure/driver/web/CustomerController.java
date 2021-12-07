package com.hu.bep3.vkb5.user.infrastructure.driver.web;

import com.hu.bep3.vkb5.user.core.application.CustomerCommandHandler;
import com.hu.bep3.vkb5.user.core.application.CustomerQueryHandler;
import com.hu.bep3.vkb5.user.core.application.command.RegisterCustomer;
import com.hu.bep3.vkb5.user.core.application.query.GetCustomerById;
import com.hu.bep3.vkb5.user.core.domain.model.Customer;
import com.hu.bep3.vkb5.user.infrastructure.driver.request.RegisterCustomerRequest;
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
}
