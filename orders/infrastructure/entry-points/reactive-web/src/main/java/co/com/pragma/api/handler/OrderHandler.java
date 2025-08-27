package co.com.pragma.api.handler;

import co.com.pragma.api.dto.request.OrderRequestDTO;
import co.com.pragma.api.mapper.OrderMapperDTO;
import co.com.pragma.model.order.Order;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
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
@Tag(name = "Order", description = "Operations related to order")
public class OrderHandler {

    private final IOrderUseCase orderUseCase;
    private final Validator validator;
    private final OrderMapperDTO orderMapperDTO;

    /**
     * Handles the HTTP request to save an order.
     *
     * @param request the incoming server request containing a OrderRequestDTO in the body
     * @return a Mono containing the ServerResponse with the saved order or an error
     */
    public Mono<ServerResponse> saveOrderCase(ServerRequest request) {
        return request.bodyToMono(OrderRequestDTO.class)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Request body cannot be empty")))
                .flatMap(this::validateOrderRequest)
                .flatMap(this::saveAndRequest);
    }

    /**
     * Validates the order request DTO.
     *
     * @param dto the order request DTO to validate
     * @return a Mono containing the validated DTO
     * @throws ConstraintViolationException if any validation errors occur
     */
    private Mono<OrderRequestDTO> validateOrderRequest(OrderRequestDTO dto) {
        Set<ConstraintViolation<OrderRequestDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String errorMsg = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("Invalid data");
            log.warn("Validation failed: {}", errorMsg);
            throw new ConstraintViolationException(violations);
        }
        return Mono.just(dto);
    }

    /**
     * Registers the order and prepares the server response.
     *
     * @param dto the validated order request DTO
     * @return a Mono containing the ServerResponse with the saved order
     */
    private Mono<ServerResponse> saveAndRequest(OrderRequestDTO dto) {
        Order order = orderMapperDTO.toModel(dto);
        return orderUseCase.saveOrder(order)
                .doOnSuccess(o -> log.info("Order successfully registered: {}", o))
                .map(orderMapperDTO::toResponse)
                .flatMap(o -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(o));
    }

    /**
     * Registers the order and prepares the server response.
     *
     * @param request the validated order request DTO
     * @return a Mono containing the ServerResponse with all orders
     */
    public Mono<ServerResponse> getAllOrders(ServerRequest request) {
        return ServerResponse.ok().body(orderUseCase.getAllOrders(), Order.class);
    }

}
