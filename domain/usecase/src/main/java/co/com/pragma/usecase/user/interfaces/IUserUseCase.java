package co.com.pragma.usecase.user.interfaces;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Mono;

public interface IUserUseCase {
    Mono<User> saveUser(User user);
    // Mono<Boolean> existEmailAddress(String emailAddress);
}
