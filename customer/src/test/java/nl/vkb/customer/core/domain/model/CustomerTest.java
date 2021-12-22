package nl.vkb.customer.core.domain.model;

import nl.vkb.customer.core.domain.exception.AddressAlreadyBoundException;
import nl.vkb.customer.core.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
	private Customer customer;
	private String validEmail;
	private String invalidEmail;

	@BeforeEach
	public void beforeEach(){
		this.validEmail = "john.doe@gmail.com";
		this.invalidEmail = "john.doe#hotmail.com";
		this.customer = new Customer("John", "Doe", this.validEmail);
	}

	@Test
	@DisplayName("Instantiating a Customer with an invalid email should throw an exception")
	void newCustomerWithInvalidEmail(){
		assertThrows(InvalidEmailException.class, ()->{
			Customer invalidCustomer = new Customer("John", "Doe", this.invalidEmail);
		});
	}

	@Test
	@DisplayName("Change the existing email with a valid one")
	void changeEmailHappyFlow(){
		customer.changeEmail(this.validEmail);
		assertEquals(this.validEmail, customer.getEmail());
	}

	@Test
	@DisplayName("Changing the email with an invalid one should throw an exception")
	void changeEmailWithInvalidMail(){
		assertThrows(InvalidEmailException.class, ()->{
			customer.changeEmail(this.invalidEmail);
		});
	}

	@Test
	@DisplayName("Adding an Address without conflict should reflect in the collection")
	void addAddressHappyFlow() {
		Address address = new Address("Valley View Road", 54, "B", "1324FH");
		customer.addAddress(address);
		assertEquals(1, customer.getAddresses().size());
		assertEquals(address, customer.getAddresses().toArray()[0]);
	}

	@Test
	@DisplayName("Adding an identical Address should throw an exception")
	void addExistingAddress() {
		Address address = new Address("Valley View Road", 54, "B", "1324FH");
		Address addressDuplicate = new Address("Valley View Road", 54, "B", "1324FH");
		customer.addAddress(address);
		assertThrows(AddressAlreadyBoundException.class, ()->{
			// Attempt to add the same address, even though it's a different object
			customer.addAddress(addressDuplicate);
		});
	}

	@ParameterizedTest
	@DisplayName("Validate emails")
	@MethodSource("provideValidEmails")
	void validateValidEmails(String email){
		assertDoesNotThrow(()->{
			customer.changeEmail(email);
		});
	}
	static Stream<Arguments> provideValidEmails() {
		return Stream.of(
				Arguments.of("johndoe@gmail.com"),
				Arguments.of("john.doe@gmail.com"),
				Arguments.of("john-doe@gmail.com"),
				Arguments.of("johndoe@gmail.hu.nl"),
				Arguments.of("john_doe@gmail.com"),
				Arguments.of("Johan.Doe@gmail.com")
		);
	}

	@ParameterizedTest
	@DisplayName("Validate emails")
	@MethodSource("provideInvalidEmails")
	void validateInvalidEmails(String email){
		assertThrows(InvalidEmailException.class, ()->{
			customer.changeEmail(email);
		});
	}
	static Stream<Arguments> provideInvalidEmails() {
		return Stream.of(
				Arguments.of(".john.doe@gmail.com"),
				Arguments.of("john.doe@gmail.com."),
				Arguments.of("*john.doe@gmail.com"),
				Arguments.of("john.doe#gmail.com"),
				Arguments.of("john.doe.@gmail.com"),
				Arguments.of("john.doe@gmail.com-"),
				Arguments.of("john.doe@.com-")
		);
	}
}
