package nl.vkb.order.infrastructure.driver.web.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
@Data
public class RegisterOrderRequest {
    @NotBlank
    public UUID customer;
    @NotBlank
    public List<UUID> dishes;
}
