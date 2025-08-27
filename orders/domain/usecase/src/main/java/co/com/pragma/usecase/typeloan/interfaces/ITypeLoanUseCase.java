package co.com.pragma.usecase.typeloan.interfaces;

import co.com.pragma.model.typeloan.TypeLoan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ITypeLoanUseCase {
    Flux < TypeLoan > getAllTypeLoan();

    Mono < TypeLoan > getByIdTypeLoan(Long idTypeLoan);

    Mono<Boolean> validateTypeLoan(TypeLoan typeLoan, BigDecimal amount);
}
