package co.com.pragma.usecase.order.interfaces;

import co.com.pragma.model.order.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderUseCase {
    Mono < Order > saveOrder(Order order);

    Flux < Order > getAllOrders();
}
