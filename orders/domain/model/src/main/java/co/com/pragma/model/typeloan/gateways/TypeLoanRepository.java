package co.com.pragma.model.typeloan.gateways;

import co.com.pragma.model.typeloan.TypeLoan;
import reactor.core.publisher.Mono;

public interface TypeLoanRepository {
    Mono <TypeLoan> existByIdTypeLoan(Long idTypeLoan);
    Mono < Boolean > isValidToRange(TypeLoan typeLoan);
}
