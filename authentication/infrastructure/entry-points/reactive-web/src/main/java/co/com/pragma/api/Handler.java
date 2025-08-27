package co.com.pragma.api;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.model.user.User;
import co.com.pragma.usecase.user.interfaces.IUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to users")
public class Handler {

    private final IUserUseCase userUseCase;
    private final Validator validator;
    private final UserMapperDTO userMapperDTO;

    /**
     * Handles the HTTP request to save a user.
     *
     * @param request the incoming server request containing a UserRequestDTO in the body
     * @return a Mono containing the ServerResponse with the saved user or an error
     */
    public Mono < ServerResponse > saveUserCase(ServerRequest request) {
        return request.bodyToMono ( UserRequestDTO.class )
                .switchIfEmpty ( Mono.error ( new IllegalArgumentException ( "Request body cannot be empty" ) ) )
                .flatMap ( this::validateUserRequest )
                .flatMap ( this::saveAndRequest );
    }

    /**
     * Validates the user request DTO.
     *
     * @param dto the user request DTO to validate
     * @return a Mono containing the validated DTO
     * @throws ConstraintViolationException if any validation errors occur
     */
    private Mono < UserRequestDTO > validateUserRequest(UserRequestDTO dto) {
        Set < ConstraintViolation < UserRequestDTO > > violations = validator.validate ( dto );
        if ( !violations.isEmpty ( ) ) {
            String errorMsg = violations.stream ( )
                    .map ( ConstraintViolation::getMessage )
                    .reduce ( (a, b) -> a + "; " + b )
                    .orElse ( "Invalid data" );
            log.warn ( "Validation failed: {}", errorMsg );
            throw new ConstraintViolationException ( violations );
        }
        return Mono.just ( dto );
    }

    /**
     * Registers the user and prepares the server response.
     *
     * @param dto the validated user request DTO
     * @return a Mono containing the ServerResponse with the saved user
     */
    private Mono < ServerResponse > saveAndRequest(UserRequestDTO dto) {
        User user = userMapperDTO.toModel ( dto );
        return userUseCase.saveUser ( user )
                .doOnSuccess ( u -> log.info ( "User successfully registered: {}", u ) )
                .map ( userMapperDTO::toResponse )
                .flatMap ( u -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( u ) );
    }
    /**
     * Get all users.
     *
     * @return a Mono containing the ServerResponse with all users
     */
    public Mono < ServerResponse > getAllUsers(ServerRequest request) {
        return ServerResponse.ok ( )
                .body ( userUseCase.getAllUsers ( ), User.class );
    }

}

