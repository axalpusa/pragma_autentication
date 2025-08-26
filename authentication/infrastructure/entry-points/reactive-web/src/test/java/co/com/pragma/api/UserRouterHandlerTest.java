package co.com.pragma.api;

import co.com.pragma.api.config.GeneralExceptionHandler;
import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.model.user.User;
import co.com.pragma.usecase.user.interfaces.IUserUseCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
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
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class for test of UserRouteHandler.
 * Use WebTestClient and Mockito.
 */
@ExtendWith(MockitoExtension.class)
class UserRouterHandlerTest {
    /**
     * Test client to perform HTTP requests.
     */
    private WebTestClient webTestClient;

    /**
     * Use case for register user
     */
    private IUserUseCase userUseCase;
    /**
     * Validation of data.
     */
    private Validator validator;
    /**
     * UserMapperDTO.
     */

    private UserMapperDTO userMapper;

    /**
     * Build a Object UserRequest for test.
     *
     * @return UserRequest
     */
    private UserRequestDTO buildRequest() {
        System.out.println ( "Init buildRequest" );
        UserRequestDTO req = new UserRequestDTO ( );
        req.setFirstName ( "axel" );
        req.setLastName ( "Puertas" );
        req.setAddress ( "Av santa rosa" );
        req.setEmailAddress ( "axalpusa11125@gmail.com" );
        req.setBirthDate ( LocalDate.parse ( "01-05-1994", DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ) ) );
        req.setDocumentId ( "48594859" );
        req.setPhoneNumber ( "973157252" );
        req.setBaseSalary ( new BigDecimal ( "700000" ) );
        req.setIdRol ( 1 );
        return req;
    }

    /**
     * Build model user to UserRequestDTO.
     *
     * @param req UserRequestDTO
     * @return User
     */
    private User buildModelFromReq(UserRequestDTO req) {
        return User.builder ( )
                .idUser ( null )
                .firstName ( req.getFirstName ( ) )
                .lastName ( req.getLastName ( ) )
                .address ( req.getAddress ( ) )
                .emailAddress ( req.getEmailAddress ( ) )
                .birthDate ( req.getBirthDate ( ) )
                .documentId ( req.getDocumentId ( ) )
                .phoneNumber ( req.getPhoneNumber ( ) )
                .baseSalary ( req.getBaseSalary ( ) )
                .idRol ( req.getIdRol () )
                .build ( );
    }

    /**
     * Config moks and WebTestClient.
     */
    @BeforeEach
    void setup() {
        userUseCase = mock ( IUserUseCase.class );
        validator = mock ( Validator.class );
        userMapper = mock ( UserMapperDTO.class );

        Handler handler = new Handler ( userUseCase, validator, userMapper );
        RouterRest routerRest = new RouterRest ( );
        RouterFunction < ServerResponse > router = routerRest.routerFunction ( handler );

        var webHandler = RouterFunctions.toWebHandler ( router );
        HttpHandler httpHandler = WebHttpHandlerBuilder.webHandler ( webHandler )
                .exceptionHandler ( new GeneralExceptionHandler ( ) )
                .build ( );

        this.webTestClient = WebTestClient.bindToServer ( new HttpHandlerConnector ( httpHandler ) ).build ( );
    }

    /**
     * Save user correct.
     */
    @Test
    @DisplayName("POST /api/v1/user/save - exito")
    void saveUserCorrect() {
        System.out.println ( "Init case save user correct" );
        UserRequestDTO req = buildRequest ( );
        User toSave = buildModelFromReq ( req );
        User saved = toSave.toBuilder ( ).build ( );
        UserResponseDTO response = new UserResponseDTO ( );
        response.setIdUser ( saved.getIdUser ( ) );
        response.setFirstName ( saved.getFirstName ( ) );
        response.setLastName ( saved.getLastName ( ) );
        response.setAddress ( saved.getAddress ( ) );
        response.setEmailAddress ( saved.getEmailAddress ( ) );
        response.setBirthDate ( saved.getBirthDate ( ) );
        response.setDocumentId ( saved.getDocumentId ( ) );
        response.setPhoneNumber ( saved.getPhoneNumber ( ) );
        response.setBaseSalary ( saved.getBaseSalary ( ) );

        when ( userMapper.toModel ( any ( UserRequestDTO.class ) ) ).thenReturn ( toSave );
        when ( userUseCase.saveUser ( any ( User.class ) ) ).thenReturn ( Mono.just ( saved ) );
        when ( userMapper.toResponse ( any ( User.class ) ) ).thenReturn ( response );

        webTestClient.post ( )
                .uri ( "/api/v1/user/save" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isOk ( )
                .expectHeader ( ).contentTypeCompatibleWith ( MediaType.APPLICATION_JSON )
                .expectBody ( )
                .jsonPath ( "$.emailAddress" ).isEqualTo ( req.getEmailAddress ( ) );
        System.out.println ( "End case save user correct" );

    }

    /**
     * Validation error
     */
    @Test
    @DisplayName("POST /api/v1/user/save - calidation_error")
    void saveUserValidationError() {
        System.out.println ( "Init casevalidation error" );
        @SuppressWarnings("unchecked")
        ConstraintViolation < UserRequestDTO > violation = Mockito.mock ( ConstraintViolation.class );
        when ( violation.getMessage ( ) ).thenReturn ( "First name is required" );
        when ( validator.validate ( any ( UserRequestDTO.class ) ) ).thenReturn ( Set.of ( violation ) );

        UserRequestDTO req = buildRequest ( );
        req.setFirstName ( "" );

        webTestClient.post ( )
                .uri ( "/api/v1/user/save" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isBadRequest ( )
                .expectBody ( )
                .jsonPath ( "$.error" ).isNotEmpty ( );
        System.out.println ( "End case validation error" );

    }

    /**
     * Exist Email Address.
     */
    @Test
    @DisplayName("POST /api/v1/user/save - exist_mail_address")
    void saveUserExistEmailAddress() {
        System.out.println ( "Init case exist email address" );
        UserRequestDTO req = buildRequest ( );
        User toSave = buildModelFromReq ( req );
        when ( userMapper.toModel ( any ( UserRequestDTO.class ) ) ).thenReturn ( toSave );
        when ( userUseCase.saveUser ( any ( User.class ) ) )
                .thenReturn ( Mono.error ( new IllegalArgumentException ( "Email address duplicate." ) ) );

        webTestClient.post ( )
                .uri ( "/api/v1/user/save" )
                .contentType ( MediaType.APPLICATION_JSON )
                .bodyValue ( req )
                .exchange ( )
                .expectStatus ( ).isBadRequest ( )
                .expectBody ( )
                .jsonPath ( "$.error" ).isEqualTo ( "Email address duplicate." );
        System.out.println ( "End case exist email address" );
    }

}