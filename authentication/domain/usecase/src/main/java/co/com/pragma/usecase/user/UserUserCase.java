package co.com.pragma.usecase.user;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.interfaces.IUserUseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Use case for register a new user
 */
@AllArgsConstructor
public class UserUserCase implements IUserUseCase {
    private final UserRepository userRepository;

    /**
     * Validate and register new user.
     *
     * @param user new user
     * @return Mono<User> new user
     */
    @Override
    public Mono < User > saveUser(User user) {
        return validateEmailAddress ( user )
                .flatMap ( this::saveNewUser );
    }

    /**
     * Validate email address duplicate
     *
     * @param user User a validar
     * @return Mono<User> user whit email address duplicate
     */
    private Mono < User > validateEmailAddress(User user) {
        return userRepository.findByEmailAddress ( user.getEmailAddress ( ) )
                .flatMap ( existingUser ->
                        Mono.error ( new IllegalArgumentException ( "Email address duplicate." ) )
                )
                .switchIfEmpty ( Mono.just ( user ) )
                .cast ( User.class );
    }

    /**
     * Return all users.
     *
     * @return Flux<User> get all
     */
    @Override
    public Flux < User > getAllUsers() {
        return userRepository.findAll ( );
    }

    /**
     * Register a ner user in repository.
     *
     * @param user new User
     * @return Mono<User> save new user
     */
    private Mono < User > saveNewUser(User user) {
        return userRepository.save ( user );
    }
}
