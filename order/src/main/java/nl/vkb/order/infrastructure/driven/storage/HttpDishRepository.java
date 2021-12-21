package nl.vkb.order.infrastructure.driven.storage;

import nl.vkb.order.core.port.data.DishRepository;
import nl.vkb.order.infrastructure.driven.exception.DishServiceUnavailableException;
import nl.vkb.order.infrastructure.driven.exception.DishUnavailableException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

public class HttpDishRepository implements DishRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpDishRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public double getPriceByDishes (String dishes) {
        URI uri = URI.create(this.rootPath+"/order/dish/price/"+dishes);
        double totalPrice = 0;
        try {
            DishResult dishResult = this.client.getForObject(uri, DishResult.class);
            if (dishResult.available) {
                totalPrice = dishResult.price;
            }
            else throw new DishUnavailableException(dishResult.unavailableDishes);

        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
                throw new DishServiceUnavailableException();
            }
        return totalPrice;
    }
}
