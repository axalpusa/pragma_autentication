package co.com.pragma.api.openapi;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.api.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class UserOpenApi {
   /* private final String SUCCESS = "Success";
    private final String SUCCESS_CODE = String.valueOf ( HttpStatus.OK.value ( ) );
    private final String CREATED_CODE = String.valueOf ( HttpStatus.CREATED.value ( ) );
    private final String BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase ( );
    private final String BAD_REQUEST_CODE = String.valueOf ( HttpStatus.BAD_REQUEST.value ( ) );
    private final String INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase ( );
    private final String INTERNAL_ERROR_CODE = String.valueOf ( HttpStatus.INTERNAL_SERVER_ERROR.value ( ) );

    public Builder saveUser(Builder builder) {
        return builder
                .operationId ( "saveUser" )
                .description ( "Create a new user" )
                .tag ( "User" )
                .requestBody ( requestBodyBuilder ( )
                        .required ( true )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( UserResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( CREATED_CODE ).description ( "User created" )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( UserResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( BAD_REQUEST_CODE ).description ( BAD_REQUEST )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder getAllUsers(Builder builder) {
        return builder
                .operationId ( "getAllUsers" )
                .description ( "Get all recorded users" )
                .tag ( "User" )
                .response ( responseBuilder ( ).responseCode ( SUCCESS_CODE ).description ( SUCCESS )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( UserRequestDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder updateUser(Builder builder) {
        return builder
                .operationId ( "updateUSer" )
                .description ( "Update an existing user" )
                .tag ( "User" )
                .requestBody ( requestBodyBuilder ( )
                        .required ( true )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( UserResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( SUCCESS_CODE ).description ( "User updated" )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( UserResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( BAD_REQUEST_CODE ).description ( BAD_REQUEST )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder deleteUser(Builder builder) {
        return builder
                .operationId ( "deleteUser" )
                .description ( "Delete a user by ID" )
                .tag ( "User" )
                .parameter ( parameterBuilder ( )
                        .name ( "idUSer" )
                        .description ( "User ID" )
                        .in ( ParameterIn.PATH )
                        .required ( true )
                        .schema ( schemaBuilder ( ).implementation ( String.class ) )
                        .example ( "ff06f58b-a067-4f17-bd8a-e4946b27b153" ) )
                .response ( responseBuilder ( ).responseCode ( String.valueOf ( HttpStatus.NO_CONTENT.value ( ) ) )
                        .description ( "User deleted" ) )
                .response ( responseBuilder ( ).responseCode ( BAD_REQUEST_CODE ).description ( BAD_REQUEST )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }*/

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/users",
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.POST,
                    beanClass = UserHandler.class,
                    beanMethod = "listenSaveUser",
                    operation = @Operation(
                            operationId = "saveUser",
                            summary = "Crear un usuario",
                            tags = { "Usuarios" }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/users/all",
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.GET,
                    beanClass = UserHandler.class,
                    beanMethod = "listenGetAllUsers",
                    operation = @Operation(
                            operationId = "getAllUsers",
                            summary = "Listar todos los usuarios",
                            tags = { "Usuarios" }
                    )
            )
    })
    public RouterFunction < ServerResponse > userRoutesDoc() {
        return RouterFunctions.route( RequestPredicates.GET("/__dummy__"), req -> ServerResponse.ok().build());
    }
}
