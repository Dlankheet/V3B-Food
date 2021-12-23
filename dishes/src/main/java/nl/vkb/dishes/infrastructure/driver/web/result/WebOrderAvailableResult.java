package nl.vkb.dishes.infrastructure.driver.web.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class WebOrderAvailableResult {
    public final boolean available;
    public final List<String> unavailableDishes;
    public final double price;
}
