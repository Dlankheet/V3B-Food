package nl.vkb.order;

import nl.vkb.order.core.port.data.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class OrderConnectionTests {

    @Autowired
    private OrderRepository repository;
    @Autowired
    RabbitTemplate template;
    @Test
    void mongoConnects() {
        assertDoesNotThrow(()->repository.findAll());
    }
    @Test
    void rabbitConnects() {
        assertDoesNotThrow(()->template.convertAndSend("exampleRoutingKey","test"));
    }

}
