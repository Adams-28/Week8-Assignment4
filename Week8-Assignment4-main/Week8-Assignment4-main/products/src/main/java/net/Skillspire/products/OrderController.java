package net.Skillspire.products;


import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customers")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> getCustomerOrder(
            @PathVariable("customerId") Long customerId,
            @PathVariable("orderId") Long orderId) {

        Order order = orderService.getCustomerOrder(customerId, orderId);

        return ResponseEntity.ok(order);

    }



}
