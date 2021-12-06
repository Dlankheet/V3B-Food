package com.hu.bep3.vkb5.user.domain;

import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
@EqualsAndHashCode
public class Customer {
	@Id
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private List<Address> addressList;

	public Customer(String firstName, String lastName, String email, List<Address> addressList) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.addressList = addressList;
	}

	public void order(int orderId){
		// do an order
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
