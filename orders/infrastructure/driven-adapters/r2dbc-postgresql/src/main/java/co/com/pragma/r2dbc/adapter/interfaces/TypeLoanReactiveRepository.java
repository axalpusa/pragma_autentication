package co.com.pragma.r2dbc.adapter.interfaces;

import co.com.pragma.r2dbc.entities.TypeLoanEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface TypeLoanReactiveRepository extends ReactiveCrudRepository < TypeLoanEntity, Long >, ReactiveQueryByExampleExecutor < TypeLoanEntity > {
    Mono < TypeLoanEntity > findByIdTypeLoan(Long idTypeLoan);
}
