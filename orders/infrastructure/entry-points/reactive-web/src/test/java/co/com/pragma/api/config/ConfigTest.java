package co.com.pragma.api.config;

import co.com.pragma.api.handler.OrderHandler;
import co.com.pragma.api.mapper.OrderMapperDTO;
import co.com.pragma.api.routerrest.OrderRouterRest;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = {OrderRouterRest.class, OrderHandler.class})
@WebFluxTest
@Import({
        OrderRouterRest.class,
        OrderHandler.class,
        CorsConfig.class,
        SecurityHeadersConfig.class,
        ConfigTest.MockBeans.class
})
class ConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @TestConfiguration
    static class MockBeans {
        @Bean
        IOrderUseCase registrarUsuarioUseCase() {
            return mock ( IOrderUseCase.class );
        }

        @Bean
        Validator validator() {
            return mock ( Validator.class );
        }

        @Bean
        OrderMapperDTO orderDTOMapper() {
            return mock ( OrderMapperDTO.class );
        }
    }

    @Test
    void securityHeadersShouldBePresent() {
        webTestClient.post ( )
                .uri ( "/api/v1/solicitud" )
                .bodyValue ( "{}" )
                .exchange ( )
                .expectStatus ( ).is4xxClientError ( )
                .expectHeader ( ).valueEquals ( "Content-Security-Policy",
                        "default-src 'self'; frame-ancestors 'self'; form-action 'self'" )
                .expectHeader ( ).valueEquals ( "Strict-Transport-Security", "max-age=31536000;" )
                .expectHeader ( ).valueEquals ( "X-Content-Type-Options", "nosniff" )
                .expectHeader ( ).valueEquals ( "Cache-Control", "no-store" )
                .expectHeader ( ).valueEquals ( "Pragma", "no-cache" )
                .expectHeader ( ).valueEquals ( "Referrer-Policy", "strict-origin-when-cross-origin" );
    }

}