package co.com.pragma.api.routerrest;

import co.com.pragma.api.dto.response.TypeLoanResponseDTO;
import co.com.pragma.api.handler.TypeLoanHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TypeLoanRouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/tipoPrestamo/{idTypeLoan}",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.GET,
                    beanClass = TypeLoanHandler.class,
                    beanMethod = "getByIdTypeLoan",
                    operation = @Operation(
                            operationId = "getByIdTypeLoan",
                            summary = "Get type loan by ID",
                            parameters = {
                                    @Parameter(
                                            name = "idTypeLoan",
                                            in = ParameterIn.PATH,
                                            required = true,
                                            description = "ID of the type loan",
                                            schema = @Schema(type = "long")
                                    )
                            },
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Successful response",
                                            content = @Content(schema = @Schema(implementation = TypeLoanResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Invalid request"
                                    )
                            }
                    )
            )
    })
    public RouterFunction < ServerResponse > typeLoanRoutes(TypeLoanHandler typeLoanHandler) {
        return route ( GET ( "/api/v1/tipoPrestamo/all" ), typeLoanHandler::getAllTypeLoan )
                .andRoute ( GET ( "/api/v1/tipoPrestamo/{idTypeLoan}" ), typeLoanHandler::getByIdTypeLoan );
    }
}
