package co.com.pragma.usecase.typeloan;

import co.com.pragma.model.typeloan.TypeLoan;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.typeloan.interfaces.ITypeLoanUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * Use case for get type loan
 */
@AllArgsConstructor
public class TypeloanUseCase implements ITypeLoanUseCase {
    private final TypeLoanRepository typeLoanRepository;

    /**
     * Return all type order.
     *
     * @return Flux<TypeLoan> get all
     */
    @Override
    public Flux < TypeLoan > getAllTypeLoan() {
        return typeLoanRepository.findAll ( );
    }

    @Override
    public Mono < TypeLoan > getByIdTypeLoan(Long idTypeLoan) {
        return typeLoanRepository.getByIdTypeLoan ( idTypeLoan );
    }

    @Override
    public Mono<Boolean> validateTypeLoan(TypeLoan typeLoan, BigDecimal amount) {
        boolean isValid = amount.compareTo(typeLoan.getMinimumAmount()) >= 0
                && amount.compareTo(typeLoan.getMaximumAmount()) <= 0;
        return Mono.just(isValid);
    }

}
