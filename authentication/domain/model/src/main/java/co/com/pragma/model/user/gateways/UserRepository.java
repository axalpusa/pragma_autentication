package co.com.pragma.model.user.gateways;

import co.com.pragma.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface for operations the persistence
 */
public interface UserRepository {
    Mono < User > save(User user);

    Mono < User > findByEmailAddress(String emailAddress);

    Flux < User > findAll();
}
