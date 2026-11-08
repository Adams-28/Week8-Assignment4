package net.Skillspire.products;


import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;

    public OrderService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Transactional
    public Order getCustomerOrder(Long customerId, Long orderId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Customer not found"
        ));

        return customer.getOrders()
                .stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This is not the customer's order"
                ));
    }
}
