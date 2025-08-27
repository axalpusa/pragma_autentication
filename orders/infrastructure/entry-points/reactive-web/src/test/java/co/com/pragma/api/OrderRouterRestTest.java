package co.com.pragma.api;

import co.com.pragma.api.config.GeneralExceptionHandler;
import co.com.pragma.api.dto.request.OrderRequestDTO;
import co.com.pragma.api.dto.response.OrderResponseDTO;
import co.com.pragma.api.handler.OrderHandler;
import co.com.pragma.api.mapper.OrderMapperDTO;
import co.com.pragma.api.routerrest.OrderRouterRest;
import co.com.pragma.model.order.Order;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import jakarta.validation.ConstraintViolation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.test.web.reactive.server.HttpHandlerConnector;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test of OrderRouteHandler.
 * Use WebTestClient and Mockito.
 */
@ExtendWith(MockitoExtension.class)
class OrderRouterRestTest {

    private WebTestClient webTestClient;
    private IOrderUseCase iorderUseCase;
    private Validator validator;
    private OrderMapperDTO orderMapper;

    /**
     * Build a Object OrderRequest for test.
     *
     * @return OrderRequest
     */
    private OrderRequestDTO buildRequest() {
        System.out.println ( "Init buildRequest" );
        OrderRequestDTO req = new OrderRequestDTO ( );
        req.setMount ( new BigDecimal ( "1000" ) );
        req.setDocumentId ( "48295730" );
        req.setEmailAddress ( "axalpusa1125@gmail.com" );
        req.setTermMonths ( 12 );
        req.setIdTypeLoan ( 1 );
        return req;
    }

    /**
     * Build model order to OrderRequestDTO.
     *
     * @param req OrderRequestDTO
     * @return Order
     */
    private Order buildModelFromReq(OrderRequestDTO req) {
        System.out.println ( "Init buildModelFromReq" );
        return Order.builder ( )
                .idOrder ( null )
                .mount ( req.getMount ( ) )
                .documentId ( req.getDocumentId ( ) )
                .emailAddress ( req.getEmailAddress ( ) )
                .emailAddress ( req.getEmailAddress ( ) )
                .termMonths ( req.getTermMonths ( ) )
                .documentId ( req.getDocumentId ( ) )
                .idTypeLoan ( req.getIdTypeLoan ( ) )
                .build ( );
    }

    /**
     * Config moks and WebTestClient.
     */
    @BeforeEach
    void setup() {
        iorderUseCase = mock ( IOrderUseCase.class );
        validator = mock ( jakarta.validation.Validator.class );
        orderMapper = mock ( OrderMapperDTO.class );

        OrderHandler handler = new OrderHandler ( iorderUseCase, validator, orderMapper );
        OrderRouterRest routerRest = new OrderRouterRest ( );
        RouterFunction < ServerResponse > router = routerRest.orderRoutes ( handler );

        var webHandler = RouterFunctions.toWebHandler ( router );
        HttpHandler httpHandler = WebHttpHandlerBuilder.webHandler ( webHandler )
                .exceptionHandler ( new GeneralExceptionHandler ( ) )
                .build ( );

        this.webTestClient = WebTestClient.bindToServer ( new HttpHandlerConnector ( httpHandler ) ).build ( );
    }

    /**
     * Save order correct.
     */
    @Test
    @DisplayName("POST /api/v1/solicitud - exito")
    void saveOrderCorrect() {
        System.out.println ( "Init case save order correct" );
        OrderRequestDTO req = buildRequest ( );
        Order toSave = buildModelFromReq ( req );
        Order saved = toSave.toBuilder ( ).build ( );
        OrderResponseDTO response = new OrderResponseDTO ( );
        response.setIdOrder ( saved.getIdOrder ( ) );
        response.setMount ( saved.getMount ( ) );
        response.setTermMonths ( saved.getTermMonths ( ) );
        response.setIdTypeLoan ( saved.getIdTypeLoan ( ) );
        response.setEmailAddress ( saved.getEmailAddress ( ) );
        response.setDocumentId ( saved.getDocumentId ( ) );

        when ( orderMapper.toModel ( any ( OrderRequestDTO.class ) ) ).thenReturn ( toSave );
        when ( iorderUseCase.saveOrder ( any ( Order.class ) ) ).thenReturn ( Mono.just ( saved ) );
        when ( orderMapper.toResponse ( any ( Order.class ) ) ).thenReturn ( response );

        webTestClient.post ( )
                .uri ( "/api/v1/solicitud" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isOk ( )
                .expectHeader ( ).contentTypeCompatibleWith ( MediaType.APPLICATION_JSON )
                .expectBody ( );
        System.out.println ( "End case save order correct" );
    }

    /**
     * Save order correct.
     */
    @Test
    @DisplayName("POST /api/v1/solicitud - error")
    void saveOrderValidateError() {
        System.out.println ( "Init case save order error" );
        ConstraintViolation < OrderRequestDTO > violation = Mockito.mock ( ConstraintViolation.class );
        when ( violation.getMessage ( ) ).thenReturn ( "First name is required" );
        when ( validator.validate ( any ( OrderRequestDTO.class ) ) ).thenReturn ( Set.of ( violation ) );

        OrderRequestDTO req = buildRequest ( );
        req.setEmailAddress ( "" );

        webTestClient.post ( )
                .uri ( "/api/v1/solicitud" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isBadRequest ( )
                .expectBody ( )
                .jsonPath ( "$.error" ).isNotEmpty ( );
        System.out.println ( "End case save order error" );
    }

    @Test
    @DisplayName("POST /api/v1/usuarios - not_exist_type_loan")
    void saveUserNotExistTypeLoan() {
        System.out.println ( "Init case not exist type loan" );
        OrderRequestDTO req = buildRequest ( );
        Order toSave = buildModelFromReq ( req );
        when ( orderMapper.toModel ( any ( OrderRequestDTO.class ) ) ).thenReturn ( toSave );
        when ( iorderUseCase.saveOrder ( any ( Order.class ) ) )
                .thenReturn ( Mono.error ( new IllegalArgumentException ( "Type loan not found." ) ) );

        webTestClient.post ( )
                .uri ( "/api/v1/solicitud" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isBadRequest ( )
                .expectBody ( )
                .jsonPath ( "$.error" ).isEqualTo ( "Type loan not found." );
        System.out.println ( "End case not exist type loan" );
    }
}
