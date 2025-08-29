package co.com.pragma.api.openapi;

import co.com.pragma.api.dto.request.RolRequestDTO;
import co.com.pragma.api.dto.response.RolResponseDTO;
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

public class RolOpenApi {
   /* private final String SUCCESS = "Success";
    private final String SUCCESS_CODE = String.valueOf ( HttpStatus.OK.value ( ) );
    private final String CREATED_CODE = String.valueOf ( HttpStatus.CREATED.value ( ) );
    private final String BAD_REQUEST = HttpStatus.BAD_REQUEST.getReasonPhrase ( );
    private final String BAD_REQUEST_CODE = String.valueOf ( HttpStatus.BAD_REQUEST.value ( ) );
    private final String INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase ( );
    private final String INTERNAL_ERROR_CODE = String.valueOf ( HttpStatus.INTERNAL_SERVER_ERROR.value ( ) );

    public Builder saveRol(Builder builder) {
        return builder
                .operationId ( "saveRol" )
                .description ( "Create a new rol" )
                .tag ( "Rol" )
                .requestBody ( requestBodyBuilder ( )
                        .required ( true )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( RolRequestDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( CREATED_CODE ).description ( "Rol created" )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( RolResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( BAD_REQUEST_CODE ).description ( BAD_REQUEST )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder getAllRols(Builder builder) {
        return builder
                .operationId ( "getAllRols" )
                .description ( "Get all recorded rols" )
                .tag ( "Rol" )
                .response ( responseBuilder ( ).responseCode ( SUCCESS_CODE ).description ( SUCCESS )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( RolRequestDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( MediaType.APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder updateRol(Builder builder) {
        return builder
                .operationId ( "updateRol" )
                .description ( "Update an existing rol" )
                .tag ( "Rol" )
                .requestBody ( requestBodyBuilder ( )
                        .required ( true )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( RolResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( SUCCESS_CODE ).description ( "Rol updated" )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( RolResponseDTO.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( BAD_REQUEST_CODE ).description ( BAD_REQUEST )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) )
                .response ( responseBuilder ( ).responseCode ( INTERNAL_ERROR_CODE ).description ( INTERNAL_ERROR )
                        .content ( contentBuilder ( ).mediaType ( APPLICATION_JSON_VALUE )
                                .schema ( schemaBuilder ( ).implementation ( ErrorResponse.class ) ) ) );
    }

    public Builder deleteRol(Builder builder) {
        return builder
                .operationId ( "deleteRol" )
                .description ( "Delete a rol by ID" )
                .tag ( "Rol" )
                .parameter ( parameterBuilder ( )
                        .name ( "idRol" )
                        .description ( "Rol ID" )
                        .in ( ParameterIn.PATH )
                        .required ( true )
                        .schema ( schemaBuilder ( ).implementation ( String.class ) )
                        .example ( "ff06f58b-a067-4f17-bd8a-e4946b27b153" ) )
                .response ( responseBuilder ( ).responseCode ( String.valueOf ( HttpStatus.NO_CONTENT.value ( ) ) )
                        .description ( "Rol deleted" ) )
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
                   path = "/api/v1/rol",
                   produces = { MediaType.APPLICATION_JSON_VALUE },
                   method = RequestMethod.POST,
                   beanClass = UserHandler.class,
                   beanMethod = "listenSaveRol",
                   operation = @Operation(
                           operationId = "saveRol",
                           summary = "Crear un rol",
                           tags = { "Rol" }
                   )
           ),
           @RouterOperation(
                   path = "/api/v1/rol/all",
                   produces = { MediaType.APPLICATION_JSON_VALUE },
                   method = RequestMethod.GET,
                   beanClass = UserHandler.class,
                   beanMethod = "listenGetAllRols",
                   operation = @Operation(
                           operationId = "getAllRol",
                           summary = "Listar todos los roles",
                           tags = { "Rol" }
                   )
           )
   })
   public RouterFunction < ServerResponse > rolRoutesDoc() {
       return RouterFunctions.route( RequestPredicates.GET("/__dummy__"), req -> ServerResponse.ok().build());
   }
}
