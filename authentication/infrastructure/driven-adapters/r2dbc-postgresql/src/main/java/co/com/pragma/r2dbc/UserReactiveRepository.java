package co.com.pragma.r2dbc;

import co.com.pragma.r2dbc.entities.UserEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserReactiveRepository extends ReactiveCrudRepository < UserEntity, Long >, ReactiveQueryByExampleExecutor < UserEntity > {

    Mono < UserEntity > findByEmailAddress(String emailAddress);
}
