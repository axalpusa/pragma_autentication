package co.com.pragma.api;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.model.user.User;
import co.com.pragma.usecase.user.saveuser.interfaces.IUserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {

    private final IUserUseCase userUseCase;
    private final Validator validator;
    private final UserMapperDTO userMapperDTO;
    private static final String ERROR_KEY = "LOG: ";

    public Mono<ServerResponse> saveUserCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(this::validarUsuarioRequest)     // validar DTO -> Mono<User>
                .flatMap(this::registrarYResponder)       // ejecutar caso de uso -> Mono<User>
                .flatMap(user -> ServerResponse.ok()
                        .bodyValue(user))                 // devolver respuesta
                .onErrorResume(ex ->
                        manejarError(ex)                      // manejar error -> Mono<ServerResponse>
                );
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .body(userUseCase.getAllUsers(), User.class);
    }

    private Mono < ServerResponse > registrarYResponder(UserRequestDTO dto) {
        User user = userMapperDTO.toModel ( dto );
        return userUseCase.saveUser ( user )
                .doOnSuccess ( u -> log.info ( "Usuario registrado exitosamente: {}", u ) )
                .map ( userMapperDTO::toResponse )
                .flatMap ( u -> ServerResponse.ok ( ).contentType ( MediaType.APPLICATION_JSON ).bodyValue ( u ) );
    }

    private Mono < UserRequestDTO > validarUsuarioRequest(UserRequestDTO dto) {
        Set < ConstraintViolation < UserRequestDTO > > violations = validator.validate ( dto );
        if ( !violations.isEmpty ( ) ) {
            String errorMsg = violations.stream ( )
                    .map ( ConstraintViolation::getMessage )
                    .reduce ( (a, b) -> a + "; " + b )
                    .orElse ( "Datos inválidos" );
            log.warn ( "Validación fallida: {}", errorMsg );
            return Mono.error ( new IllegalArgumentException ( errorMsg ) );
        }
        return Mono.just ( dto );
    }

    private Mono < ServerResponse > manejarError(Throwable e) {
        if ( e instanceof IllegalArgumentException ) {
            log.warn ( "Error procesando la petición: {}", e.getMessage ( ) );
            return ServerResponse.badRequest ( ).bodyValue ( Map.of ( ERROR_KEY, e.getMessage ( ) ) );
        }
        log.error ( "Error procesando la petición: {}", e.getMessage ( ), e );
        return ServerResponse.badRequest ( ).bodyValue ( Map.of ( ERROR_KEY,
                "Ocurrió un error procesando la solicitud. Verifique los datos e intente nuevamente o contacte a soporte." ) );
    }

}

