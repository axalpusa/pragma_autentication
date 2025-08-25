package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<Boolean> userExistsByEmailAddress(String emailAddress);
    Mono<Boolean> userExistsByIdUser(Long idUser);
    Mono<User> save(User user);
    Mono<User> update(User user);
    Mono<User> findById(Long idUser);
    Mono<User> findByEmailAddress(String emailAddress);
    Mono<Void> deleteUserByIdUser(Long idUser);
    Flux<User> findAll();
}
