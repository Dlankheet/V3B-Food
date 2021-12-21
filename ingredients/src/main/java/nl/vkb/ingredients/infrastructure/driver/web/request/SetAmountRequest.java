package nl.vkb.ingredients.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SetAmountRequest {
	private final int stock;
}
