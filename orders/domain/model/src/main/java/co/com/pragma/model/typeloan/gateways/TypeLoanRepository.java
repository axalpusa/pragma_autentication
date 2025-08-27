package co.com.pragma.model.typeloan.gateways;

import co.com.pragma.model.typeloan.TypeLoan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface for operations the persistence
 */
public interface TypeLoanRepository {
    Mono < TypeLoan > getByIdTypeLoan(Long idTypeLoan);

    Flux < TypeLoan > findAll();
}
