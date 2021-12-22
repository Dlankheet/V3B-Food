package nl.vkb.dishes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class DishesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DishesApplication.class, args);
    }
}
