package com.hu.bep3.vkb5.customer.core.domain.model;

import java.util.*;

import com.hu.bep3.vkb5.customer.Utils;
import com.hu.bep3.vkb5.customer.core.domain.event.CustomerEvent;
import com.hu.bep3.vkb5.customer.core.domain.event.EmailChanged;
import com.hu.bep3.vkb5.customer.core.domain.exception.AddressAlreadyBoundException;
import com.hu.bep3.vkb5.customer.core.domain.exception.InvalidEmailException;
import lombok.AccessLevel;
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
	private Set<UUID> reviews;
	@Transient
	@Getter(AccessLevel.NONE)
	private List<CustomerEvent> events = new ArrayList<>();

	/**
	 * Constructs the Customer, throws an exception if the given email is invalid
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @exception InvalidEmailException
	 */
	public Customer(String firstName, String lastName, String email) throws InvalidEmailException {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.validateEmail(email);
		this.email = email;
		this.addresses = new HashSet<>();
		this.orderHistory = new HashSet<>();
		this.reviews = new HashSet<>();
	}

	/**
	 * Add an Order ID to our Order history
	 * @param orderId
	 */
	public void orderFood(UUID orderId){
		orderHistory.add(orderId);
	}

	/**
	 * Add a Review ID to our list of Reviews
	 * @param reviewId
	 */
	public void reviewOrder(UUID reviewId) {
		reviews.add(reviewId);
	}

	/**
	 * Change the existing email after validating the new email
	 * @param newEmail
	 * @exception InvalidEmailException
	 */
	public void changeEmail(String newEmail){
		this.validateEmail(newEmail);
		this.email = newEmail;
		this.events.add(new EmailChanged(id, newEmail));
	}

	/**
	 * Attempt to add an address to the list of Addresses
	 * @param address
	 * @exception AddressAlreadyBoundException
	 */
	public void addAddress(Address address) throws AddressAlreadyBoundException{
		boolean added = this.addresses.add(address);
		if(!added){
			throw new AddressAlreadyBoundException();
		}
	}

	/**
	 * Remove an Address from the Address list
	 * @param address
	 */
	public void removeAddress(Address address){
		this.addresses.remove(address);
	}

	/**
	 * Validate an Email(String) by checking it against a given regex pattern
	 * @param email
	 * @throws InvalidEmailException
	 */
	private void validateEmail(String email) throws InvalidEmailException {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if(!Utils.patternMatches(email, regexPattern)){
			throw new InvalidEmailException();
		}
	}

	/**
	 * Clear(remove all) the event list of this class
	 */
	public void clearEvents() {
		this.events.clear();
	}

	/**
	 * Retrieve the list of events bound to this class
	 * @return a list of events
	 */
	public List<CustomerEvent> listEvents() {
		return events;
	}
}
