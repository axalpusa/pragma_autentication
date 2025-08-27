package co.com.pragma.usecase.order;

import co.com.pragma.model.order.Order;
import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import co.com.pragma.usecase.typeloan.interfaces.ITypeLoanUseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Use case for register a new order
 */
@AllArgsConstructor
public class OrderUseCase implements IOrderUseCase {
    private final OrderRepository orderRepository;
    private final TypeLoanRepository typeLoanRepository;
    private final ITypeLoanUseCase typeloanUseCase;

    /**
     * Validate and register new order.
     *
     * @param order new order
     * @return Mono<Order> new order
     */
    @Override
    public Mono < Order > saveOrder(Order order) {
        return typeLoanRepository.getByIdTypeLoan ( order.getIdTypeLoan ( ).longValue ( ) )
                .switchIfEmpty ( Mono.error ( new IllegalArgumentException ( "Type loan not found." ) ) )
                .flatMap ( typeLoan ->
                        typeloanUseCase.validateTypeLoan ( typeLoan, order.getMount ( ) )
                                .flatMap ( isValid -> {
                                    if ( !isValid ) {
                                        return Mono.error ( new IllegalArgumentException ( "El monto no está dentro del rango permitido" ) );
                                    }
                                    order.setIdStatus ( 1 );
                                    return saveNewOrder ( order );
                                } )
                );
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
