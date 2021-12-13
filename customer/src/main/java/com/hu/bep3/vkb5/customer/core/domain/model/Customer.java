package com.hu.bep3.vkb5.customer.core.domain.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customers")
@Getter
@ToString
@EqualsAndHashCode
public class Customer {
	@Id
	private UUID id;
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String email;
	@Indexed @DBRef
	private Set<Address> addresses;
	@DBRef
	private OrderHistory orderHistory;

	public Customer(String firstName, String lastName, String email) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addresses = new HashSet<>();
		this.orderHistory = new OrderHistory();
	}

	public void order(int orderId){
		// order food
	}

	public void reviewOrder(int orderId){
		// review an order here.
	}

	public void changeEmail(String newEmail){
		// change the email after some validation
	}

	public void addAddress(Address address){
		// add an address
	}

	public void removeAddress(Address address){
		// remove an address
	}
}
