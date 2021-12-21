package nl.hu.v3bfood_dishes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class V3BFoodDishesApplication {
    public static void main(String[] args) {
        SpringApplication.run(V3BFoodDishesApplication.class, args);
    }
}
