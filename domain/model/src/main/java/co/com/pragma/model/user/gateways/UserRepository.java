package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> findByIdUser(Long idUser);

    Mono<User> saveUser(User user);

    Mono<Void> deleteUserByIdUser(Long idUser);

    Mono<Boolean> existEmailAddress(String emailAddress);

    Flux<User> findAllUsers();
}
