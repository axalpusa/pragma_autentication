package co.com.pragma.usecase.order;

import co.com.pragma.model.order.Order;
import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.model.typeloan.TypeLoan;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Use case for register a new order
 */
@AllArgsConstructor
public class OrderUseCase implements IOrderUseCase {
    private final OrderRepository orderRepository;
    //private final TypeLoanRepository typeLoanRepository;
    /**
     * Validate and register new order.
     *
     * @param order new order
     * @return Mono<Order> new order
     */
    @Override
    public Mono<Order> saveOrder(Order order) {
        return validateTypeLoad(order)
                .flatMap(this::saveNewOrder);
    }

    /**
     * Return all orders.
     *
     * @return Flux<Order> get all
     */
    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private Mono<Order> validateTypeLoad(Order order) {
        Mono<TypeLoan> loan = Ma
        return typeLoanRepository.existByIdTypeLoan(order.getIdTypeLoan().longValue())
                .flatMap(exists -> exists
                        ? Mono.just(order)
                        : Mono.error(new IllegalArgumentException("Type load no exist"))
                );
    }

    /**
     * Register a new order in repository.
     *
     * @param order new order
     * @return Mono<Order> save new order
     */
    private Mono<Order> saveNewOrder(Order order) {
        return orderRepository.save(order);
    }
}
