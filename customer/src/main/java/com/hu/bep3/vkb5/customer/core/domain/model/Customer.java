package com.hu.bep3.vkb5.customer.core.domain.model;

import java.util.*;

import com.hu.bep3.vkb5.customer.Utils;
import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;
import com.hu.bep3.vkb5.customer.core.domain.event.EmailChanged;
import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.InvalidEmailException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
	private Set<UUID> orderHistory;
	private Set<String> reviews;
	@Transient
	private List<CustomerEvent> events = new ArrayList<>();

	public Customer(String firstName, String lastName, String email) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		if(this.validateEmail(email)){this.email = email;}
		this.addresses = new HashSet<>();
		this.orderHistory = new HashSet<>();
		this.reviews = new HashSet<>();
	}

	public void addOrderToHistory(UUID orderId){
//		orderHistory.add(orderId);
	}

	public void changeEmail(String newEmail){
		if(this.validateEmail(newEmail)){
			this.email = newEmail;
		}
		this.events.add(new EmailChanged(id, newEmail));
	}

	public void addAddress(Address address){
		boolean added = this.addresses.add(address);
		if(!added){
			throw new AddressAlreadyBoundException();
		}
	}

	public void removeAddress(Address address){
		this.addresses.remove(address);
	}

	private boolean validateEmail(String email) throws InvalidEmailException {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if(Utils.patternMatches(email, regexPattern)){
			return true;
		}
		throw new InvalidEmailException();
	}

	public void clearEvents() {
		this.events.clear();
	}

	public List<CustomerEvent> listEvents() {
		return events;
	}
}
