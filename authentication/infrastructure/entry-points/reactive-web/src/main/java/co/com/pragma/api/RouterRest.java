package co.com.pragma.api;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/user/save",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = Handler.class,
                    beanMethod = "saveUserCase",
                    operation = @Operation(
                            operationId = "saveUserCase",
                            summary = "Register a user",
                            requestBody = @RequestBody(required = true, content = @Content(schema = @Schema(implementation = UserRequestDTO.class))),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Registered user",
                                            content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                                    @ApiResponse(responseCode = "400", description = "Invalid request")
                            }
                    )
            )
    })
    public RouterFunction < ServerResponse > routerFunction(Handler handler) {
        return route ( GET ( "/api/v1/user/all" ), handler::getAllUsers )
                .andRoute ( POST ( "/api/v1/user/save" ), handler::saveUserCase );
    }
}
