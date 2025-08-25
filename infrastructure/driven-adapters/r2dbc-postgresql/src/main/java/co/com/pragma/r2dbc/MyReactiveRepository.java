package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.entities.UserEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MyReactiveRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<User> findByEmailAddress(String emailAddress);

    Mono<Void> deleteUserByIdUser(Long idUser);

    Mono<User> update(User user);

    Mono<Boolean> userExistsByIdUser(Long idUser);

    Mono<Boolean> userExistsByEmailAddress(String emailAddress);
}
