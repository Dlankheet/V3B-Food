package nl.vkb.order.infrastructure.driver.web;

import nl.vkb.order.core.application.OrderCommandHandler;
import nl.vkb.order.core.application.OrderQueryHandler;
import nl.vkb.order.core.application.command.*;
import nl.vkb.order.core.application.query.FindAllOrderByCustomerId;
import nl.vkb.order.core.application.query.GetOrderById;
import nl.vkb.order.core.domain.Order;
import nl.vkb.order.core.domain.exception.OrderNotFoundException;
import nl.vkb.order.core.domain.exception.OrderStatusException;
import nl.vkb.order.infrastructure.driven.exception.DishServiceUnavailableException;
import nl.vkb.order.infrastructure.driven.exception.DishUnavailableException;
import nl.vkb.order.infrastructure.driver.web.request.RegisterOrderRequest;
import nl.vkb.order.infrastructure.driver.web.response.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCommandHandler commandHandler;
    private final OrderQueryHandler queryHandler;

    public OrderController (OrderCommandHandler commandHandler, OrderQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping("/add")
    public OrderDto registerOrder(@RequestBody RegisterOrderRequest request){
        Order order = this.commandHandler.handle(new RegisterOrder(request.customer, new HashSet<>(request.dishes)));
        return new OrderDto(order);
    }
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable UUID id){
        return new OrderDto(this.queryHandler.handle(new GetOrderById(id)));
    }
    @GetMapping("/customer/{id}")
    public List<OrderDto> getAllByCustomer(@PathVariable String id){
        return this.queryHandler.handle(new FindAllOrderByCustomerId(UUID.fromString(id))).stream().
                map(OrderDto::new).toList();
    }
    @GetMapping("/all")
    public List<OrderDto> getAllOrder(){
        return this.queryHandler.handle().stream().
                map(OrderDto::new).toList();
    }
    @PatchMapping("/accept/{id}")
    public OrderDto acceptOrder(@PathVariable UUID id){
        Order order = this.commandHandler.handle(new AcceptOrder(id));
        return new OrderDto(order);
    }
    @PatchMapping("/cancel/{id}")
    public OrderDto cancelOrder(@PathVariable UUID id){
        Order order = this.commandHandler.handle(new CancelOrder(id));
        return new OrderDto(order);
    }
    @PatchMapping("/deny/{id}")
    public OrderDto denyOrder(@PathVariable UUID id){
        Order order = this.commandHandler.handle(new DenyOrder(id));
        return new OrderDto(order);
    }
    @PatchMapping("/delivering/{id}")
    public OrderDto deliveringOrder(@PathVariable UUID id){
        Order order = this.commandHandler.handle(new DeliveringOrder(id));
        return new OrderDto(order);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleOrderStatus(OrderStatusException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleDishUnavailable(DishUnavailableException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> handleDishServiceUnavailable(DishServiceUnavailableException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
