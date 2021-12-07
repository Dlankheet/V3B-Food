package com.hu.bep3.vkb5.user.infrastructure.driver.web;

import com.hu.bep3.vkb5.user.core.domain.model.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
//	private final CustomerCommandHandler commandHandler;
//	private final CustomerQueryHandler queryHandler;
//
//	public CustomerController(CustomerCommandHandler commandHandler, CustomerQueryHandler queryHandler) {
//		this.commandHandler = commandHandler;
//		this.queryHandler = queryHandler;
//	}
//
//	@PostMapping
//	public Customer registerCustomer(@Valid @RequestBody RegisterCustomerRequest request) {
//		return this.commandHandler.handle(
//				new RegisterCustomer(request.name, request.email)
//		);
//	}
}
