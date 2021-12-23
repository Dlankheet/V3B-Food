package nl.vkb.order.infrastructure.driver.web.request;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
public class RegisterOrderRequest {
    @NotBlank
    public final UUID customer;
    @NotBlank
    public final List<UUID> dishes;
}
