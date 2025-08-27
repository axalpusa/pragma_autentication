package co.com.pragma.r2dbc.adapter;

import co.com.pragma.model.order.Order;
import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.r2dbc.adapter.interfaces.OrderReactiveRepository;
import co.com.pragma.r2dbc.entities.OrderEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Adapter for Order persistence operations using R2DBC.
 */
@Slf4j
@Component
public class OrderReactiveRepositoryAdapter extends ReactiveAdapterOperations <
        Order, OrderEntity, Long, OrderReactiveRepository
        > implements OrderRepository {
    public OrderReactiveRepositoryAdapter(OrderReactiveRepository repository, ObjectMapper mapper) {
        super ( repository, mapper, d -> mapper.mapBuilder ( d, Order.OrderBuilder.class ).build ( ) );
    }

    /**
     * Saves an order to the database transactional.
     *
     * @param order new order
     * @return Mono<Order> new order
     */
    @Override
    @Transactional
    public Mono < Order > save(Order order) {
        OrderEntity entity = mapper.map ( order, OrderEntity.class );
        return repository.save ( entity )
                .map ( saved -> mapper.map ( saved, Order.class ) )
                .doOnError ( e -> log.error ( "Error saving user to database: {}", e.getMessage ( ), e ) );
    }

    @Override
    public Flux < Order > findAll() {
        return repository.findAll ( )
                .map ( this::toOrder );
    }

    private Order toOrder(OrderEntity entity) {
        return mapper.map ( entity, Order.class );
    }
}
