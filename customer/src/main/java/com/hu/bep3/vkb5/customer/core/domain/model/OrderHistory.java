package com.hu.bep3.vkb5.customer.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public class OrderHistory {
	private Set<String> orders;

}
