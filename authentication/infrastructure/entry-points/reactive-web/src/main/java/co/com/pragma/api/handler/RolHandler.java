package co.com.pragma.api.handler;

import co.com.pragma.api.dto.request.RolRequestDTO;
import co.com.pragma.api.dto.response.RolResponseDTO;
import co.com.pragma.model.rol.Rol;
import co.pragma.crediya.usecase.rol.RolUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RolHandler {

    private final RolUseCase rolUseCase;
    private final ObjectMapper objectMapper;

    public Mono < ServerResponse > listenSaveRol(ServerRequest request) {
        return request.bodyToMono ( RolRequestDTO.class )
                .map ( rol -> objectMapper.convertValue ( rol, Rol.class ) )
                .flatMap ( rolUseCase::saveRol )
                .flatMap ( savedRol -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( savedRol ) );
    }
     public Mono < ServerResponse > listenUpdateRol(ServerRequest request) {
        return request.bodyToMono ( RolResponseDTO.class )
                .map ( rol -> objectMapper.convertValue ( rol, Rol.class ) )
                .flatMap ( rolUseCase::updateRol )
                .flatMap ( savedRol -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( savedRol ) );
    }

    public Mono < ServerResponse > listenGetAllRols(ServerRequest request) {
        return ServerResponse.ok ( )
                .contentType ( MediaType.TEXT_EVENT_STREAM )
                .body ( rolUseCase.getAllRol ( ), RolResponseDTO.class );
    }

    public Mono < ServerResponse > listenGetRolById(ServerRequest request) {
        return Mono.fromCallable ( () -> request.pathVariable ( "idRol" ) )
                .map ( String::trim )
                .filter ( item -> !item.isBlank ( ) )
                .map ( UUID::fromString )
                .flatMap ( rolUseCase::getRolById )
                .flatMap ( rol -> ServerResponse.ok ( )
                        .contentType ( MediaType.APPLICATION_JSON )
                        .bodyValue ( rol ) )
                .switchIfEmpty ( ServerResponse.notFound ( ).build ( ) );
    }

    public Mono < ServerResponse > listenDeleteRol(ServerRequest request) {

        return Mono.fromCallable ( () -> request.pathVariable ( "idRol" ) )
                .map ( String::trim )
                .filter ( item -> !item.isBlank ( ) )
                .map ( UUID::fromString )
                .flatMap ( id -> rolUseCase.deleteRolById ( id )
                        .then ( ServerResponse.noContent ( ).build ( ) )
                )
                .switchIfEmpty ( ServerResponse.notFound ( ).build ( ) );
    }

}
