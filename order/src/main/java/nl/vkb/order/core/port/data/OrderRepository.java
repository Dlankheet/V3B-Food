package nl.vkb.order.core.port.data;

import nl.vkb.order.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;


public interface OrderRepository extends MongoRepository<Order, UUID> {
    List<Order> findAllByCustomer(String customer);
}
