package co.com.pragma.usecase.order;

import co.com.pragma.model.order.Order;
import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderUseCase {
    private final OrderRepository orderRepository;
    private final TypeLoanRepository typeLoanRepository;

    @Override
    public Mono < Order > saveOrder(Order order) {
        return validateTypeLoad ( order )
                .flatMap ( this::saveNewOrder );
    }

    /**
     * Return all orders.
     *
     * @return Flux<Order> get all
     */
    @Override
    public Flux < Order > getAllOrders() {
        return orderRepository.findAll ( );
    }

    private Mono < Order > validateTypeLoad(Order order) {
        return typeLoanRepository.existByIdTypeLon ( order.getIdTypeLoan ( ).longValue ( ) )
                .flatMap ( exists -> exists
                        ? Mono.just ( order )
                        : Mono.error ( new IllegalArgumentException ( "Type load no exist" ) )
                );
    }

    /**
     * Register a new order in repository.
     *
     * @param order new order
     * @return Mono<Order> save new order
     */
    private Mono < Order > saveNewOrder(Order order) {
        return orderRepository.save ( order );
    }
}
