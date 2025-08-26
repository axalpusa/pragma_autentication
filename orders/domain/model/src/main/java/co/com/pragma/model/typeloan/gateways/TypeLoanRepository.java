package co.com.pragma.model.typeloan.gateways;

import reactor.core.publisher.Mono;

public interface TypeLoanRepository {
    Mono < Boolean > existByIdTypeLon(Long idTypeLoan);
    //Mono < Boolean > betwenMaximum(Long idTypeLoan);
}
