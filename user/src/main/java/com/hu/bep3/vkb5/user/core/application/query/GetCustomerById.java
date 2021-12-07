package com.hu.bep3.vkb5.user.core.application.query;

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
