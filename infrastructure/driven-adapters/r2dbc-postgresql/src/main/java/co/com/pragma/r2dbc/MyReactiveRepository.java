package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.entities.UserEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyReactiveRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<User> findByIdUser(Long idUser);

    Mono<User> saveUser(User user);

    Mono<Void> deleteUserByIdUser(Long idUser);

    Mono<Boolean> existEmailAddress(String emailAddress);

    Flux<User> findAllUsers();
}
