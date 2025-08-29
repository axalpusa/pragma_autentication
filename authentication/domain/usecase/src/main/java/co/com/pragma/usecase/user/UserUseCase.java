package co.com.pragma.usecase.user;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;

    public Mono<User> saveUser(User user) {
        return validateUser(user)
                .flatMap(this::saveNewUser)
                .onErrorResume(e -> {
                    // Capturar cualquier error de validación y envolverlo en un objeto de error
                    return Mono.error(new ValidationException ( Collections.singletonList ( e.getMessage ( ) ) ));
                });
    }

    private Mono<User> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        if (isBlank(user.getFirstName())) {
            errors.add("First name is required.");
        }
        if (isBlank(user.getLastName())) {
            errors.add("Last name is required.");
        }
        if (isBlank(user.getEmailAddress())) {
            errors.add("Email address is required.");
        }
        if (isBlank(user.getAddress())) {
            errors.add("Address is required.");
        }
        if (isBlank(user.getDocumentId())) {
            errors.add("Document ID is required.");
        }
        if (user.getBaseSalary() == null ||
                user.getBaseSalary().doubleValue() < 0 ||
                user.getBaseSalary().doubleValue() > 15_000_000) {
            errors.add("Base salary must be between 0 and 15,000,000.");
        }

        if (!errors.isEmpty()) {
            return Mono.error(new ValidationException(errors));
        }

        return userRepository.findByEmailAddress(user.getEmailAddress())
                .flatMap(existingUser ->
                        Mono.<User>error(new ValidationException(
                                List.of("Email address duplicate.")
                        ))
                )
                .switchIfEmpty(Mono.just(user));
    }
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private Mono < User > saveNewUser(User user) {
        return userRepository.save ( user );
    }

    public Mono < User > updateUser(User user) {
        return userRepository.save ( user );
    }

    public Mono < User > getUserById(UUID id) {
        return userRepository.findById ( id );
    }

    public Mono < Void > deleteUserById(UUID id) {
        return userRepository.deleteById ( id );
    }

    public Flux < User > getAllUsers() {
        return userRepository.findAll ( );
    }


}
