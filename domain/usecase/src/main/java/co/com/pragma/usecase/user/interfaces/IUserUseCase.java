package co.com.pragma.usecase.user.interfaces;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserUseCase {
    Mono<User> saveUser(User user);

    Mono<Boolean> userExistsByIdUser(Long idUser);

    Mono<Boolean> userExistsByEmailAddress(String emailAddress);

    Mono<User> updateUser(User user);

    Flux<User> getAllUsers();

    Mono<Void> deleteUserById(Long idUser);

    Mono<User> getUserByEmailAddress(String emailAddress);

    Mono<User> getUserByIdUser(Long idUser);
}
