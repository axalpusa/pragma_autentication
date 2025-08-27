package co.com.pragma.api.routerrest;

import co.com.pragma.api.dto.request.OrderRequestDTO;
import co.com.pragma.api.handler.OrderHandler;
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
public class OrderRouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/solicitud",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = OrderHandler.class,
                    beanMethod = "saveOrderCase",
                    operation = @Operation(
                            operationId = "saveOrderCase",
                            summary = "Register an order",
                            requestBody = @RequestBody(required = true, content = @Content(schema = @Schema(implementation = OrderRequestDTO.class))),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Registered order",
                                            content = @Content(schema = @Schema(implementation = OrderRequestDTO.class))),
                                    @ApiResponse(responseCode = "400", description = "Invalid request")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(OrderHandler orderHandler) {
        return route(GET("/api/v1/all"), orderHandler::getAllOrders)
                .andRoute(POST("/api/v1/solicitud"), orderHandler::saveOrderCase);
    }
}
