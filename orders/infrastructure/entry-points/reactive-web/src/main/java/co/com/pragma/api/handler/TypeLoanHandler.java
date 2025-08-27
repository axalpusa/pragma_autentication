package co.com.pragma.api.handler;

import co.com.pragma.model.typeloan.TypeLoan;
import co.com.pragma.usecase.typeloan.interfaces.ITypeLoanUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "TypeLoan", description = "Operations related to type loan")
public class TypeLoanHandler {

    private final ITypeLoanUseCase typeLoanUseCase;

    /**
     * Handles the HTTP request to type loan by id.
     *
     * @return a Mono containing the ServerResponse with the order or an error
     */
    public Mono < ServerResponse > getByIdTypeLoan(ServerRequest request) {
        Long idTypeLoan = Long.parseLong ( request.pathVariable ( "idTypeLoan" ) );
        return ServerResponse.ok ( ).body ( typeLoanUseCase.getByIdTypeLoan ( idTypeLoan ), TypeLoan.class );
    }

    /**
     * Get all Type loan.
     *
     * @return a Mono containing the ServerResponse with all type loan
     */
    public Mono < ServerResponse > getAllTypeLoan(ServerRequest request) {
        return ServerResponse.ok ( ).body ( typeLoanUseCase.getAllTypeLoan ( ), TypeLoan.class );
    }

}
