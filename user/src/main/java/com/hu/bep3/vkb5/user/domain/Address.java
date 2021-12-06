package com.hu.bep3.vkb5.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
@EqualsAndHashCode
public class Address {
	private String street;
	private int number;
	private String additionalLetter;
	private String postalCode;
}
