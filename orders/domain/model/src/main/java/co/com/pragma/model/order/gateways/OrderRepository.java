package co.com.pragma.model.order.gateways;

import co.com.pragma.model.order.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface for operations the persistence
 */
public interface OrderRepository {
    Mono < Order > save(Order order);
    Flux <Order> findAll();
}
