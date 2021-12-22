package nl.vkb.order.infrastructure.driver.web.request;

import lombok.Data;

import java.util.List;
import javax.validation.constraints.NotBlank;
@Data
public class RegisterOrderRequest {
    @NotBlank
    public String customer;
    @NotBlank
    public List<String> dishes;
}
