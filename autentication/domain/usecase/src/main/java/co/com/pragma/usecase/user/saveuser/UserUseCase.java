package co.com.pragma.usecase.user.saveuser;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.saveuser.interfaces.IUserUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor()
public class UserUseCase implements IUserUseCase {

    private final UserRepository userRepository;

    public Mono < User > saveUser(User user) {
        return validateByEmailAddress ( user )
                .flatMap ( this::guardarNuevoUsuario );
    }

    @Override
    public Flux < User > getAllUsers() {
        return userRepository.findAll();
    }

    private Mono < User > validateByEmailAddress(User user) {
        return userRepository.findByEmailAddress ( user.getEmailAddress ( ) )
                .flatMap ( existingUser ->
                        Mono.error ( new IllegalArgumentException ( "El correo electrónico ya se encuentra registrado." ) )
                )
                .switchIfEmpty ( Mono.just ( user ) )
                .cast ( User.class );
    }

    private Mono < User > guardarNuevoUsuario(User user) {
        return userRepository.save ( user );
    }

}
