package com.hu.bep3.vkb5.customer.core.domain.model;

import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
	private Customer customer;

	@BeforeEach
	public void beforeEach(){
		customer = new Customer("John", "Doe", "john.doe@gmail.com");
	}

	@Test
	// There are no addresses yet, and we simply add one with no conflict
	void addAddressHappyFlow() {
		Address address = new Address("Valley View Road", 54, "B", "1324FH");
		customer.addAddress(address);
		assertEquals(1, customer.getAddresses().size());
		assertEquals(address, customer.getAddresses().toArray()[0]);
	}

	@Test
	// There is one address already present, and we try to add an identical one
	void addExistingAddress() {
		Address address = new Address("Valley View Road", 54, "B", "1324FH");
		Address addressDuplicate = new Address("Valley View Road", 54, "B", "1324FH");
		customer.addAddress(address);
		assertThrows(AddressAlreadyBoundException.class, ()->{
			// Attempt to add the same address, even though it's a different object
			customer.addAddress(addressDuplicate);
		});
	}
}