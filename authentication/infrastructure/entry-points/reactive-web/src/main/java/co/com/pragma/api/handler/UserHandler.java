package co.com.pragma.api.handler;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.model.user.User;
import co.com.pragma.usecase.user.UserUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase userUseCase;
    private final ObjectMapper objectMapper;
    private final UserMapperDTO userMapperDTO;

    public Mono<ServerResponse> listenSaveUser(ServerRequest request) {
        return request.bodyToMono(UserRequestDTO.class)
                .switchIfEmpty(Mono.error(new ValidationException(
                        List.of("Request body cannot be empty")
                )))
                .map(userMapperDTO::toModel)
                .flatMap(userUseCase::saveUser)
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user))
                .onErrorResume(ValidationException.class, ex ->
                        ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("errors", ex.getErrors()))
                );
    }

    public Mono < ServerResponse > listenUpdateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono ( UserResponseDTO.class )
                .map ( user -> objectMapper.convertValue ( user, User.class ) )
                .flatMap ( userUseCase::updateUser )
                .flatMap ( savedUser -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( savedUser ) );
    }
    public Mono < ServerResponse > listenGetAllUsers(ServerRequest request) {
        return ServerResponse.ok ( )
                .contentType ( MediaType.TEXT_EVENT_STREAM )
                .body ( userUseCase.getAllUsers ( ), UserResponseDTO.class );
    }

    public Mono < ServerResponse > listenGetUserById(ServerRequest serverRequest) {
        return Mono.fromCallable ( () -> serverRequest.pathVariable ( "idUser" ) )
                .map ( String::trim )
                .filter ( item -> !item.isBlank ( ) )
                .map ( UUID::fromString )
                .flatMap ( userUseCase::getUserById )
                .flatMap ( user -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( user ) )
                .switchIfEmpty ( ServerResponse.notFound ( ).build ( ) );
    }

    public Mono < ServerResponse > listenDeleteUser(ServerRequest serverRequest) {

        return Mono.fromCallable ( () -> serverRequest.pathVariable ( "idUser" ) )
                .map ( String::trim )
                .filter ( item -> !item.isBlank ( ) )
                .map ( UUID::fromString )
                .flatMap ( id -> userUseCase.deleteUserById ( id )
                        .then ( ServerResponse.noContent ( ).build ( ) )
                )
                .switchIfEmpty ( ServerResponse.notFound ( ).build ( ) );
    }

}

