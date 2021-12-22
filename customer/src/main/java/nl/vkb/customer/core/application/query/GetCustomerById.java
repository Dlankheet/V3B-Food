package nl.vkb.customer.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetCustomerById {
	@NotBlank
	private final UUID id;
}
