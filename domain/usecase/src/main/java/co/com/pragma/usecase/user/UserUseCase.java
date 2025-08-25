package co.com.pragma.usecase.user;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.interfaces.IUserUseCase;
import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
@RequiredArgsConstructor
public class UserUseCase implements IUserUseCase {

    private final UserRepository userRepository;

    @Override
    public Mono<User> saveUser(User user) {
        return userRepository.userExistsByEmailAddress(user.getEmailAddress())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("Email ya existe"));
                    } else {
                        return userRepository.save(user);
                    }
                });
    }

    @Override
    public Mono<Boolean> userExistsByIdUser(Long idUser) {
        return userRepository.userExistsByIdUser(idUser);
    }

    @Override
    public Mono<Boolean> userExistsByEmailAddress(String emailAddress) {
        return userRepository.userExistsByEmailAddress(emailAddress);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<Void> deleteUserById(Long idUser) {
        return userRepository.deleteUserByIdUser(idUser);
    }

    @Override
    public Mono<User> getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public Mono<User> getUserByIdUser(Long idUser) {
        return userRepository.findById(idUser);
    }
}
