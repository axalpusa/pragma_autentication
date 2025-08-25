package co.com.pragma.api;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final UserUseCase userUseCase;
    private final RequestValidator requestValidator;
    private final UserMapperDTO userMapperDTO;

    @Transactional
    public Mono<ServerResponse> saveUserCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDTO.class)
                .flatMap(requestValidator::validate)     //  validar DTO
                .map(userMapperDTO::toModel)             //  mapear DTO → dominio
                .flatMap(userUseCase::saveUser)          //  ejecutar caso de uso
                .flatMap(user -> ServerResponse.ok()
                        .bodyValue(user))                //  devolver respuesta
                .onErrorResume(ex -> ServerResponse.badRequest()
                        .bodyValue("Error: " + ex.getMessage())); //  manejo de errores
    }

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
