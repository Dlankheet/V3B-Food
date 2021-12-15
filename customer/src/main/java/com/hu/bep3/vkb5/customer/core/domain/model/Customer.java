package com.hu.bep3.vkb5.customer.core.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.hu.bep3.vkb5.customer.Utils;
import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.InvalidEmailException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
	private Set<Address> addresses;
	private OrderHistory orderHistory;
	private Set<String> reviews;

	public Customer(String firstName, String lastName, String email) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		if(this.validateEmail(email)){this.email = email;}
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
		if(this.validateEmail(newEmail)){
			this.email = newEmail;
		}
	}

	public void addAddress(Address address){
		boolean added = this.addresses.add(address);
		if(!added){
			throw new AddressAlreadyBoundException();
		}
	}

	public void removeAddress(Address address){
		// remove an address
	}

	private boolean validateEmail(String email) throws InvalidEmailException {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if(Utils.patternMatches(email, regexPattern)){
			return true;
		}
		throw new InvalidEmailException();
	}
}
