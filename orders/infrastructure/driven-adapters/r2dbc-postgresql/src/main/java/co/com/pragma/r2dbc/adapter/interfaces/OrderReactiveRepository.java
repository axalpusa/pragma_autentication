package co.com.pragma.r2dbc.adapter.interfaces;

import co.com.pragma.r2dbc.entities.OrderEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderReactiveRepository extends ReactiveCrudRepository < OrderEntity, Long >, ReactiveQueryByExampleExecutor < OrderEntity > {

}
